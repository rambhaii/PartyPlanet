package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DasboadModel
{
    public ArrayList<Dataum> getBanerData()
    {
        return banerData;
    }
    public void setBanerData(ArrayList<Dataum> banerData)
    {
        this.banerData = banerData;
    }

    @SerializedName("data")
    @Expose
    ArrayList<Dataum> banerData;
    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;



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
