package com.example.raunak.samrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import java.nio.file.Files;

public class CustomerLoginPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        Integer[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    String TableNo;
    Spinner spin;
    Bundle b;
    Integer a;
    AutoCompleteTextView name;
    EditText num;
    TableInfo tableInfo = new TableInfo();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_page);
        name=(AutoCompleteTextView)findViewById(R.id.Customer_Name);
        num=(EditText) findViewById(R.id.Mobile_Number);

        spin = (Spinner) findViewById(R.id.Table_Number);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,number);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        //String TableNo = spin.getSelectedItem().toString();
        //Log.d("ABC","---Table no----="+TableNo);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("ABC","---Table no----="+ spin.getSelectedItem());
        a = (Integer)spin.getSelectedItem();
        Log.d("ABC", String.valueOf(a));
       // TableInfo tableInfo = new TableInfo();
        tableInfo.setTable_no(a);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*public void customerSubmit(View view)
    {
        Intent i=new Intent(this,)
    }*/

    public void homeBack(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void customerSubmit(View view)
    {

        tableInfo.setName(String.valueOf(name.getText()));
        Log.d("ABC","aaaaaaaaaaaaaaaa"+tableInfo.getName());
        tableInfo.setPhone_no(String.valueOf(num.getText()));
        Intent i=new Intent(this,Menu.class);

        i.putExtra("Table",a);
        startActivity(i);
    }
}
