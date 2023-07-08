package com.example.raunak.samrestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapterBill extends ArrayAdapter<BillInfo>
{
    private List<BillInfo> listc;
    private Context contextc;

    public OrderAdapterBill(@NonNull Context context, int resource) {
        super(context, resource);
    }

    TextView currentFoodNameC,
            removeMeal,
            quantityTextC;

    public OrderAdapterBill(Context context, List<BillInfo> myOrders) {
        super(context, 0, myOrders);
        this.listc = myOrders;
        this.contextc = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_my_bill, parent, false
            );
        }

        final BillInfo currentFoodc = getItem(position);
        currentFoodNameC = (TextView) listItemView.findViewById(R.id.bill_food);
        quantityTextC = (TextView) listItemView.findViewById(R.id.bill_quantity);


        currentFoodNameC.setText(currentFoodc.name);
        quantityTextC.setText(String.valueOf(currentFoodc.price));



        return listItemView;

    }
}
