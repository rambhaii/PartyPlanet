package com.app.partyplanets.RestaurantData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResturantData
{
    public String id;
    public String name;
    public String status;
    public String is_delete;
    public String created_at;
    public Object updated_at;
    public boolean expendable;
    String total_amt;
    String cart_id;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id)
    {
        this.cart_id = cart_id;
    }

    public ResturantData(String id, String name, String status, String is_delete, String created_at, Object updated_at)
    {
        this.id = id;
        this.name = name;
        this.status = status;
        this.is_delete = is_delete;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.expendable = false;
    }

    @SerializedName("food_menu_data")
    @Expose
    private ArrayList<FoodManue> data;

    public ArrayList<FoodManue> getData() {
        return data;
    }

    public boolean isExpendable() {
        return expendable;
    }

    public void setExpendable(boolean expendable) {
        this.expendable = expendable;
    }

    public void setData(ArrayList<FoodManue> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Object updated_at) {
        this.updated_at = updated_at;
    }
}
