package com.example.raunak.samrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends Activity {

    RadioButton CustomerLogin;
    RadioButton ManagerLogin;
    RadioButton StaffLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomerLogin=(RadioButton)findViewById(R.id.CustomerLogin);
        ManagerLogin=(RadioButton)findViewById(R.id.ManagerLogin);
        StaffLogin=(RadioButton)findViewById(R.id.StaffLogin);

    }

    public void onClickCustomer(View view)
    {
        Intent i=new Intent(this,CustomerLoginPage.class);
        startActivity(i);
    }

    public void onClickManager(View view)
    {
        Intent i=new Intent(this,ManagerPage3.class);
        startActivity(i);
    }

    public void onClickStaff(View view)
    {
        Intent i=new Intent(this,Staff_login.class);
        startActivity(i);
    }


}
