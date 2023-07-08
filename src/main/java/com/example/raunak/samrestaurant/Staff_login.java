package com.example.raunak.samrestaurant;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Staff_login extends AppCompatActivity {

    FirebaseAuth auth;
    EditText id;
    EditText pass;
    Button login;
    String url="https://samrestaurant-c3891.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        auth=FirebaseAuth.getInstance();
        id=(EditText)findViewById(R.id.Staff_Login);
        pass=(EditText)findViewById(R.id.Staff_Password);
        login=(Button)findViewById(R.id.Submit_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin(id.getText().toString(),pass.getText().toString());
            }
        });
    }

    private void signin(String id,String pass) {

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Enter all the details", Toast.LENGTH_SHORT).show();

        } else {
            auth.signInWithEmailAndPassword(id, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        Toast.makeText(Staff_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Staff_login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

   /* public void homeBack(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void staffSubmit(View view)
    {
        Intent i=new Intent(this,Orders.class);
        startActivity(i);
    }*/
}
