package com.example.raunak.samrestaurant;

import android.telephony.euicc.DownloadableSubscription;

import java.io.Serializable;

public class Dish implements Serializable {
    String name;
   // String id;
    Integer price;
    Integer no_in_cart;

    public void setQuantity(int no_in_cart)
    {
        this.no_in_cart=no_in_cart;
    }

    public Dish(String name, Integer price)
    {
        this.name=name;
        this.price=price;
        this.no_in_cart=0;

    }

    public Dish()
    {
        no_in_cart=0;
    }

    public void removeFromQuantity()
    {
        if(this.no_in_cart>0)
          this.no_in_cart -= 1;
        else
            return;

    }

    public void addToQuantity()
    {
        this.no_in_cart=no_in_cart+1;
    }

}