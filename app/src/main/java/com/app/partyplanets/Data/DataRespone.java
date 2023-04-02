package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRespone {
    @SerializedName("data")
    @Expose
    private data1 data1;

    public data1 getData1() {
        return data1;
    }

    public void setData1(data1 data1)
    {
        this.data1 = data1;
    }
    public  int status;
    public  String message;

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
