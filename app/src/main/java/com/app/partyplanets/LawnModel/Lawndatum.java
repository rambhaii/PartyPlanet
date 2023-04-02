package com.app.partyplanets.LawnModel;

public class Lawndatum
{
    String cart_id;
    String available_slot;
     int total_amount;

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getAvailable_slot() {
        return available_slot;
    }

    public void setAvailable_slot(String available_slot) {
        this.available_slot = available_slot;
    }
}
