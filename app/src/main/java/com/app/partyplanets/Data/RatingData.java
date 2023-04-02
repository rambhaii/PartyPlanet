package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingData {
    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("data")
    @Expose
    RatingDatum datum;

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

    public RatingDatum getDatum() {
        return datum;
    }

    public void setDatum(RatingDatum datum) {
        this.datum = datum;
    }
}
