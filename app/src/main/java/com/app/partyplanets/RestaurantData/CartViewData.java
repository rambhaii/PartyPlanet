package com.app.partyplanets.RestaurantData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartViewData
{
    @SerializedName("food_data")
    @Expose
    private ArrayList<Food> data;
    String sub_total;

    public ArrayList<Food> getData()
    {
        return data;
    }

    public void setData(ArrayList<Food> data) {
        this.data = data;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }
}
