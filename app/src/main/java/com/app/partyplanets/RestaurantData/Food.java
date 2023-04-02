package com.app.partyplanets.RestaurantData;

public class Food
{

    public String title;
    public String sub_title;
    public String image;
    public String id;
    public Object cart_id;
    public String user_id;
    public String listing_id;
    public String food_menu_id;
    public String price;
    public String type;
    public String qty;
    public String total_amt;
    public String created_at;
    public String vendor_id;
    private int selectAmt;

    public int getSelectAmt() {
        return selectAmt;
    }

    public void setSelectAmt(int selectAmt) {
        this.selectAmt = selectAmt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getCart_id() {
        return cart_id;
    }

    public void setCart_id(Object cart_id) {
        this.cart_id = cart_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public String getFood_menu_id() {
        return food_menu_id;
    }

    public void setFood_menu_id(String food_menu_id) {
        this.food_menu_id = food_menu_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String size;
}
