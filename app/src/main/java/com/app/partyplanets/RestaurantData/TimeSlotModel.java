package com.app.partyplanets.RestaurantData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TimeSlotModel {
    @SerializedName("data")
    @Expose
    ArrayList<TimeSlotData> data;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;

    public ArrayList<TimeSlotData> getData() {
        return data;
    }

    public void setData(ArrayList<TimeSlotData> data) {
        this.data = data;
    }

    public int getStatus() {
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
