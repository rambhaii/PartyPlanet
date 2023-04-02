package com.app.partyplanets.LawnModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Foodcategoryitem
{
    public String id;
    public String listing_id;
    public String vendor_id;
    public String plate_type_id;
    public String cat_id;
    public String choose_limit;
    public String menu_ids;
    public String status;
    public String created_at;
    public String updated_at;
    public String cat_name;
    @SerializedName("fooditems")
    @Expose
    public ArrayList<Fooditem> fooditems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getPlate_type_id() {
        return plate_type_id;
    }

    public void setPlate_type_id(String plate_type_id) {
        this.plate_type_id = plate_type_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getChoose_limit() {
        return choose_limit;
    }

    public void setChoose_limit(String choose_limit) {
        this.choose_limit = choose_limit;
    }

    public String getMenu_ids() {
        return menu_ids;
    }

    public void setMenu_ids(String menu_ids) {
        this.menu_ids = menu_ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public ArrayList<Fooditem> getFooditems() {
        return fooditems;
    }

    public void setFooditems(ArrayList<Fooditem> fooditems) {
        this.fooditems = fooditems;
    }
}
