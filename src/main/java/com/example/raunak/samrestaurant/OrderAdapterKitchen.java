package com.example.raunak.samrestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapterKitchen  extends ArrayAdapter<KitchenInfo> {

    private List<KitchenInfo> listc;
    private Context contextc;

    public OrderAdapterKitchen(@NonNull Context context, int resource) {
        super(context, resource);
    }

    TextView currentFoodNameC,
            tableNum,
            quantityTextC;

    public OrderAdapterKitchen(Context context, List<KitchenInfo> myOrders) {
        super(context, 0, myOrders);
        this.listc = myOrders;
        this.contextc = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_my_kitchen, parent, false
            );
        }

        final KitchenInfo currentFoodc = getItem(position);
        currentFoodNameC = (TextView) listItemView.findViewById(R.id.kit_name);
        quantityTextC = (TextView) listItemView.findViewById(R.id.kit_quantity);
        tableNum=(TextView) listItemView.findViewById(R.id.kit_tno);


        //currentFoodNameC.setText(currentFoodc.Kname);
       // quantityTextC.setText(String.valueOf(currentFoodc.K));



        return listItemView;

    }
}
