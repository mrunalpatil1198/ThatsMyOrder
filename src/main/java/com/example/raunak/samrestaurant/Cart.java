package com.example.raunak.samrestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.raunak.samrestaurant.Dish;
import com.example.raunak.samrestaurant.Menu;
import com.example.raunak.samrestaurant.OrderAdapterCart;
import com.example.raunak.samrestaurant.R;

import java.util.ArrayList;

class Cart extends AppCompatActivity {


    ListView storedOrdersCart;
    ArrayList<Dish> cartorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        storedOrdersCart= (ListView)findViewById(R.id.cart_food_list);
    }


    void cartadapter()
    {

        Log.d("ABC","In Cart Adapter");


        OrderAdapterCart adapter = new OrderAdapterCart(this, cartorder);

        storedOrdersCart.setAdapter(adapter);
    }
}