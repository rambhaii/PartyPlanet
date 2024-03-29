package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomGetByCarId
{
    @SerializedName("data")
    @Expose
    private SelectedRoomModel data;

    public SelectedRoomModel getData()
    {
        return data;
    }

    public void setData(SelectedRoomModel data) {
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
