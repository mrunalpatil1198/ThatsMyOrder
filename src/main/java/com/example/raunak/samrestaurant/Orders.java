package com.example.raunak.samrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

       // firebaseDatabase=FirebaseDatabase.getInstance();
     //   myRef=firebaseDatabase.getReference("Orders");
       // LV=(ListView) findViewById(R.id.ListV);






        /*myRef.addValueEventListener(new ValueEventListener() {
            int i=0;
            ArrayList<KitchenInfo> array=new ArrayList<KitchenInfo>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KitchenInfo ki=new KitchenInfo();
               String key= dataSnapshot.getKey();
                DataSnapshot foodSnapshot = dataSnapshot.child("Food");
                Iterable<DataSnapshot> foodChildren = foodSnapshot.getChildren();
                ArrayList<Temp> contacts=new ArrayList<>();
                for (DataSnapshot contact : foodChildren) {
                    Temp c = contact.getValue(Temp.class);
                    Log.d("food + quant:: ", c.food );
                    contacts.add(c);
                }
                ki.t=contacts;
                ki.tableNum=key;

                array.add(i,ki);
                i++;



            }

            OrderAdapterKitchen adapter=new OrderAdapterKitchen(this,array);
            LV.setAdapter(adapter);

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }
/*
    private void showData(DataSnapshot dataSnapshot) {

        int i=0;
        ArrayList<KitchenInfo> array=new ArrayList<KitchenInfo>();
       for(DataSnapshot ds: dataSnapshot.getChildren())
        {
            Log.d("ABC","Key"+ds.getKey());
            KitchenInfo ki=new KitchenInfo();


            while(ds.getChildren().iterator().hasNext())
            {
                ki.tableNum=ds.getKey();
                ki.Kname=ds.child().getKey();
            }
            Integer x= Math.toIntExact(ds.getChildrenCount());
            for(int y=0;y<x;y++)
            {

            }


        }

        OrderAdapterKitchen adapter=new OrderAdapterKitchen(this,array);
        LV.setAdapter(adapter);
    }*/

    public void staffBack(View view)
    {
        Intent i=new Intent(this,Staff_login.class);
        startActivity(i);
    }

    public void signoutOrder(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
