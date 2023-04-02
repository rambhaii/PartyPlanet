package com.app.partyplanets.RestaurantData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResturantModel
{
    @SerializedName("data")
    @Expose
    private ArrayList<ResturantData> data;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;

    public ArrayList<ResturantData> getData()
    {
        return data;
    }

    public void setData(ArrayList<ResturantData> data)
    {
        this.data = data;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
