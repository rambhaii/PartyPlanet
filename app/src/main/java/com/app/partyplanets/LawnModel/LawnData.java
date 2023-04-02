package com.app.partyplanets.LawnModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LawnData
{
    public String id;
    public String listing_id;
    public String amount_type;
    public String status;
    public String created_at;
    @SerializedName("foodcategoryitems")
    @Expose
    public ArrayList<Foodcategoryitem> foodcategoryitems;
    public String getId() {
        return id;
    }
    public boolean getExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    private boolean isExpand;

    public void setId(String id) {
        this.id = id;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public String getAmount_type() {
        return amount_type;
    }

    public void setAmount_type(String amount_type) {
        this.amount_type = amount_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public ArrayList<Foodcategoryitem> getFoodcategoryitems() {
        return foodcategoryitems;
    }

    public void setFoodcategoryitems(ArrayList<Foodcategoryitem> foodcategoryitems) {
        this.foodcategoryitems = foodcategoryitems;
    }
}
