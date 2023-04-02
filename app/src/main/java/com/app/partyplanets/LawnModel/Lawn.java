package com.app.partyplanets.LawnModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lawn {
    int status;
    String message;
    @SerializedName("data")
    @Expose
    public Lawndatum data;

    public Lawndatum getData() {
        return data;
    }

    public void setData(Lawndatum data) {
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
