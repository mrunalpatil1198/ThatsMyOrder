package com.example.raunak.samrestaurant;

import com.example.raunak.samrestaurant.Dish;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;


import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.R.layout.simple_list_item_1;


public class Menu extends AppCompatActivity {

    static ArrayList<Dish> cartorder = new ArrayList<Dish>();
    ArrayList<Dish> orders1;
    ArrayList<Dish> orders;

    static ListView storedOrders;
    Bundle b = new Bundle();

    public static Integer is_menu_inited = 0;
    public static ArrayList<Dish> dishes;
    static {
        dishes = new ArrayList<Dish>();
    }
    Button placeOrder;
    Integer TableNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufinal);
        storedOrders = (ListView) findViewById(R.id.selected_food_list);
        placeOrder = (Button) findViewById(R.id.placeOrder);
        //TableNo = getIntent().getExtras().getInt("Table");
        TableInfo tableInfo = new TableInfo();
        TableNo = tableInfo.getTable_no();
        Log.d("ABC", "In Menu-----------" + String.valueOf(TableNo));
        //  TableNo = getIntent().getExtras();
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foo1();
                b.putSerializable("cartorder", cartorder);
                // b.putInt("TableNo",TableNo);
                Intent i = new Intent(Menu.this, CartDemo.class);
                //   i.putExtras(b);
                i.putExtra("cart", b);
                i.putExtra("Table", TableNo);
                startActivity(i);
            }
        });
        new fetchData1().execute();
    }


    public void foo1() {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).no_in_cart > 0)
                cartorder.add(dishes.get(i));
            Log.d("ABC", "cartorder:" + cartorder.toString());
        }
        //dishes.clear();
    }

    static Integer is_loaded = 0;

    public class fetchData1 extends AsyncTask<Void, Void, Void> {

        String data = "";
        String datap = "";
        String singlep = "";

        @Override
        protected Void doInBackground(Void... voids) {
            if (is_menu_inited == 1)
            {
                return null;
            }
            try {
                dishes.clear();
                is_menu_inited = 1;
                Log.d("ABC", "connecting:");

                URL url = new URL("http://skcopaints.com/document.json");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;

                }


                JSONObject json = new JSONObject(data);
                JSONArray json_menu_array = json.getJSONArray("menu");
                Log.d("ABC", json.getJSONArray("menu").toString());
                int len = json_menu_array.length();
                len = len - 1;
                Log.d("ABC", "size:" + len);
                Integer i;
                ArrayList<String> ice_name = new ArrayList<String>();
                ArrayList<Double> price_array = new ArrayList<Double>();
                ArrayList<String> type = new ArrayList<String>();
                for (i = 0; i <= len; i++) {
                    Dish dish = new Dish();
                    JSONObject json_array_obj = json_menu_array.getJSONObject(i);
                    dish.name = json_array_obj.getString("name");
                    dish.price = json_array_obj.getInt("price");
                    //price_array.add(json_array_obj.getDouble("price"));
                    //type.add(json_array_obj.getString("subtype"));
                    Log.d("ABC", json_array_obj.toString());
                    dishes.add(i, dish);
                }
                for (int j = 0; j <= len; j++) {
                    Log.d("ABC", dishes.get(j).name);
                    Log.d("ABC", String.valueOf(dishes.get(j).price));
                    // Log.d("ABC","\n"+String.valueOf(j));
                    // Log.d("ABC", ice_name.get(i));
                    // Log.d("ABC", price_array.get(i).toString());
                    singlep = "Name: " + dishes.get(j).name + "\n" + "Price: " + String.valueOf(dishes.get(j).price) + "\n\n";
                    datap = datap + singlep;

                }
                Log.d("ABC", "DONE");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }


        public ArrayList<Dish> getDishes() {
            return dishes;
        }

        @Override
        protected void onPostExecute(Void param) {

            // Menu.data.setText(this.datap);
            //ArrayList<Dish> orders1;
            //orders1=this.getDishes();
            //Log.d("ABC","ABCs"+ String.valueOf(orders1.size()));
            /*for(int i=0;i<orders1.size();i++)
            {
                Log.d("ABC", "\n"+ String.valueOf(orders1.get(i).name+orders1.get(i).no_in_cart+orders1.get(i).price));
            }*/
            Log.d("ABC", "In postexecute." + dishes.size());
            OrderAdapter adapter = new OrderAdapter(Menu.this, dishes);

            storedOrders.setAdapter(adapter);


        }


    }


}






