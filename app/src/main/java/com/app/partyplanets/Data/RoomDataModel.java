package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RoomDataModel
{
    @SerializedName("data")
    @Expose
    private ArrayList<RoomData> data;

    public ArrayList<RoomData> getData() {
        return data;
    }

    public void setData(ArrayList<RoomData> data) {
        this.data = data;
    }

    @SerializedName("status")
    @Expose
    private int status;

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

    @SerializedName("message")
    @Expose
    private String message;
}
