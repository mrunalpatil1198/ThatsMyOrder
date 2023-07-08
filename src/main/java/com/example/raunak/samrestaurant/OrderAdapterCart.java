package com.example.raunak.samrestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapterCart extends ArrayAdapter<Dish> {

    private List<Dish> listc;
    private Context contextc;

    public OrderAdapterCart(@NonNull Context context, int resource) {
        super(context, resource);
    }

    TextView currentFoodNameC,
            removeMeal,
            quantityTextC;

    public OrderAdapterCart(Context context, List<Dish> myOrders) {
        super(context, 0, myOrders);
        this.listc = myOrders;
        this.contextc = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_my_cart, parent, false
            );
        }

        final Dish currentFoodc = getItem(position);
        currentFoodNameC = (TextView) listItemView.findViewById(R.id.cart_food);
        quantityTextC = (TextView) listItemView.findViewById(R.id.cart_quantity);
        removeMeal = (TextView) listItemView.findViewById(R.id.cancel_meal);

        currentFoodNameC.setText(currentFoodc.name);
        quantityTextC.setText(String.valueOf(currentFoodc.no_in_cart));

        removeMeal.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              listc.remove(position);
                                              notifyDataSetChanged();
                                          }
                                      }
        );

        return listItemView;

    }
}
