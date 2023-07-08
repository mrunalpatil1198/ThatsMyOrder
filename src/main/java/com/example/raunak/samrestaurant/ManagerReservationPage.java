package com.example.raunak.samrestaurant;

import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ManagerReservationPage extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_reservation_page);

        firebaseAuth.getInstance();

    }

    public void tableBack(View view)
    {
        Intent i=new Intent(this,ManagerPage3.class);
        startActivity(i);
    }

    public void signoutManager(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }


}
