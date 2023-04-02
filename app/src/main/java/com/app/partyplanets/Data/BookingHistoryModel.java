package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingHistoryModel
{


    @SerializedName("data")
    @Expose
    ArrayList<SelectedRoomModel> banerData;
    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;

    public ArrayList<SelectedRoomModel> getBanerData() {
        return banerData;
    }

    public void setBanerData(ArrayList<SelectedRoomModel> banerData) {
        this.banerData = banerData;
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
