package com.example.raunak.samrestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Dish>{
    private List<Dish> list;
    private Context context;

    TextView currentFoodName,
            currentCost,
            quantityText,
            addMeal,
            subtractMeal;

    public OrderAdapter(Context context, List<Dish> myOrders) {
        super(context, 0, myOrders);
        this.list = myOrders;
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_my_menu,parent,false
            );
        }

        final Dish currentFood = getItem(position);

        currentFoodName = (TextView)listItemView.findViewById(R.id.selected_food_name);
        currentCost = (TextView)listItemView.findViewById(R.id.selected_food_amount);
        subtractMeal = (TextView)listItemView.findViewById(R.id.minus_meal);
        quantityText = (TextView)listItemView.findViewById(R.id.quantity);
        addMeal = (TextView)listItemView.findViewById(R.id.plus_meal);


        //Set the text of the meal, amount and quantity

//            currentCost.setText("100");

//            quantityText.setText("0");
        currentFoodName.setText(currentFood.name);
        currentCost.setText(String.valueOf(currentFood.price));
        quantityText.setText(String.valueOf(currentFood.no_in_cart));



        //OnClick listeners for all the buttons on the ListView Item
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFood.addToQuantity();
                quantityText.setText("x "+ currentFood.no_in_cart);
               // currentCost.setText("Rs"+"\u20B5"+" "+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                notifyDataSetChanged();
            }
        });

        subtractMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFood.removeFromQuantity();
                quantityText.setText("x "+currentFood.no_in_cart);
             //   currentCost.setText("Rs"+"\u20B5"+" "+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                notifyDataSetChanged();
            }
        });

       /* removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        }
       );*/

        return listItemView;
    }

}
