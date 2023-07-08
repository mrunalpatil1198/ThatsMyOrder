package com.example.raunak.samrestaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Bill extends AppCompatActivity {

    //EditText block;
    Integer TableNo;
    String cName,cPhone;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ListView LV;
    TextView num,nam,amt;
    Bundle od;
    //ArrayList<Dish> cartorder1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        //block=(EditText)findViewById(R.id.Block);
        num=(TextView) findViewById(R.id.Num);
        nam=(TextView) findViewById(R.id.Name);
        amt=(TextView) findViewById(R.id.total);

        od = getIntent().getExtras().getBundle("cart");
        /*cartorder1 =(ArrayList<Dish>) od.getSerializable("cart");
        for(int i=0;i<cartorder1.size();i++)
            Log.d("ABC","--------------------"+cartorder1.get(i).name);*/
        Integer totalAmount=getIntent().getIntExtra("TotalAmt",0);

        LV=(ListView) findViewById(R.id.LV);
        TableInfo tableInfo = new TableInfo();
        TableNo = tableInfo.getTable_no();
        cName=tableInfo.getName();
        cPhone=tableInfo.getPhone_no();

        num.setText("Number:"+cPhone);
        nam.setText("Name:"+cName);
        amt.setText("Total Amount:"+totalAmount);

        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference(TableNo+"Bill");

        //DatabaseReference mChild=databaseReference.child(Integer.toString(TableNo)+"Bill");

      //  block.setText((CharSequence) mChild.getDatabase());
       // Log.d("ABC", String.valueOf(mChild.getParent()));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void sout(View view)
    {
        myRef.removeValue();
        Toast.makeText(this, "Thank you. Visit again.", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    private void showData(DataSnapshot dataSnapshot) {
        int i=0;
        ArrayList<BillInfo> array=new ArrayList<BillInfo>();
        for(DataSnapshot ds: dataSnapshot.getChildren())
        {
            Log.d("ABC","In for"+ds.getKey());
            Log.d("ABC",(Integer.toString(TableNo)+"Bill"));
            BillInfo bi=new BillInfo();
           // if((ds.getKey()).equalsIgnoreCase(Integer.toString(TableNo)+"Bill")) {
               // Log.d("ABC","In if");
              //  for(DataSnapshot ms: dataSnapshot.getChildren()) {

                    bi.name=ds.getKey();
                    Log.d("ABC", "##########" + bi.name);
                    bi.price=String.valueOf(ds.getValue());

                   Log.d("ABC", "##########" + bi.price);


                    array.add(i,bi);
                    i++;

               // }


            //}

            //bInfo.setName(ds.child(String.valueOf(i)).getValue());
        }
        OrderAdapterBill adapter=new OrderAdapterBill(this,array);
        LV.setAdapter(adapter);

    }
}
