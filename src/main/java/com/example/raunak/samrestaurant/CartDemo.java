package com.example.raunak.samrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CartDemo extends AppCompatActivity {

    ListView storedOrdersCart;
    ArrayList<Dish> corder;
    Integer totalAmount=0;
    DatabaseReference mDatabase;
    DatabaseReference mDatabase1;
    DatabaseReference mDatabase2;
    Bundle b;
    Integer TableNo;
    Button orderButton;
    String billNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        storedOrdersCart= (ListView)findViewById(R.id.cart_food_list);
        orderButton=(Button)findViewById(R.id.Orderbutton);
        // b = getIntent().getExtras();
        b=getIntent().getExtras().getBundle("cart");
        TableInfo tableInfo = new TableInfo();
        TableNo = tableInfo.getTable_no();
        billNo=tableInfo.getPhone_no();

        // mDatabase= FirebaseDatabase.getInstance().getReference(Integer.toString(TableNo));
        mDatabase= FirebaseDatabase.getInstance().getReference("Orders");
        mDatabase1= FirebaseDatabase.getInstance().getReference(Integer.toString(TableNo)+"Bill");
        mDatabase2= FirebaseDatabase.getInstance().getReference("Bill History");
        // mDatabase2=FirebaseDatabase.getInstance().getReference("OrdersTemp");
        // mDatabase= FirebaseDatabase.getInstance().getReference();
        //TableNo = getIntent().getExtras().getInt("Table");


        // TableNo=getIntent().getBundleExtra("TableNo");
        Log.d("ABC", "Table No"+String.valueOf(TableNo));
        corder =(ArrayList<Dish>) b.getSerializable("cartorder");
        //Log.w("printlist",corder.toString());
        OrderAdapterCart adapter = new OrderAdapterCart(this, corder);
        storedOrdersCart.setAdapter(adapter);
        for(int i=0;i<corder.size();i++)
        {
            Log.d("ABC","mmmmmmmmmmmmmmmmmmmmmmmmmmmm"+ String.valueOf(corder.get(i).no_in_cart));
            Log.d("ABC","mmmmmmmmmmmmmmmmmmmmmmmmmmmm"+ String.valueOf(corder.get(i).price));
            Integer x=corder.get(i).no_in_cart;
            Integer y=corder.get(i).price;
            totalAmount=totalAmount+(x*y);
            Log.d("ABC", String.valueOf(totalAmount));
        }


        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp= String.valueOf(TableNo);
                Log.d("ABC","%%%%%%%%%%%%"+temp+"%%%%%%%%%%%%");
                String temp1=Integer.toString(TableNo);


                //HashMap<String,ArrayList<Dish>> hmap=new HashMap<String, ArrayList<Dish>>();
                //hmap.put(temp1,corder);

                // mDatabase.child("Orders").push().setValue(hmap);


                for(int i=0;i<corder.size();i++) {
                    String fname = corder.get(i).name;
                    String fquantity= String.valueOf(corder.get(i).no_in_cart);
                    String fprice= String.valueOf(corder.get(i).price);
                    Integer tot=corder.get(i).price*corder.get(i).no_in_cart;
                    String totalB= String.valueOf(tot);
                 /*mDatabase.child(fname);
                 mDatabase.child(fname).child("name"+i).setValue(fname);
                 mDatabase.child("name"+i).child("quantity"+i).setValue(fquantity);*/
                    //mDatabase.child("quantity"+i).setValue(fquantity);
                    //mDatabase.child("price"+i).setValue(fprice);
                    //mDatabase.child(String.valueOf(TableNo)).child(fname).setValue(fquantity);
                    // mDatabase1.child("name").setValue(fname);
                    //  mDatabase1.child("price").setValue(fprice);
                    // mDatabase1.child("quantity").setValue(fquantity);
                    mDatabase.child(String.valueOf(TableNo)).child(fname).child("Qunatity").setValue(fquantity);
                    mDatabase1.child(fname).setValue(fquantity+"*"+fprice);
                    mDatabase2.child(billNo).child("Order").child(fname).setValue(fquantity+"*"+fprice);
                    //mDatabase2.child(String.valueOf(TableNo)).child("Food").setValue(fname+""+fquantity);

                    //mDatabase1.child("quantity"+i).setValue(fquantity);
                    //mDatabase1.child("price"+i).setValue(fprice);

                }

                mDatabase2.child(billNo).child("Total Amount").setValue(totalAmount);
                //mDatabase1.setValue(corder);
                Intent i=new Intent(CartDemo.this,Bill.class);
                i.putExtra("TotalAmt",totalAmount);
                startActivity(i);
            }
        });
    }



    /*public void goToBill(View view) {

        for(int i=0;i<corder.size();i++)
        {
            Log.d("ABC","mmmmmmmmmmmmmmmmmmmmmmmmmmmm"+ String.valueOf(corder.get(i).no_in_cart));
            Log.d("ABC","mmmmmmmmmmmmmmmmmmmmmmmmmmmm"+ String.valueOf(corder.get(i).price));
            Integer x=corder.get(i).no_in_cart;
            Integer y=corder.get(i).price;
            totalAmount=totalAmount+(x*y);
        }

        Log.d("ABC", "******************"+String.valueOf(totalAmount)+"******************");

        JSONObject obj=null;
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<corder.size();i++)
        {
            obj=new JSONObject();
            try{
                obj.put("name",corder.get(i).name);
                obj.put("price",corder.get(i).price);
                obj.put("quantity",corder.get(i).no_in_cart);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(obj);
        }

        JSONObject finalobject=new JSONObject();
        try {
            finalobject.put("finalorder",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<finalobject.length();i++)
        {
            try {
                Log.d("ABC",finalobject.toString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent i=new Intent(this,Bill.class);
        startActivity(i);
    }*/
}
