package com.app.partyplanets.LawnModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LawnResponse
{
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("data")
    @Expose
    public ArrayList<LawnData> data;

    public ArrayList<LawnData> getData() {
        return data;
    }

    public void setData(ArrayList<LawnData> data) {
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
