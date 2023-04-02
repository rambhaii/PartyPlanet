package com.app.partyplanets.LawnModel;

import java.util.ArrayList;

public class CartNumber {
    ArrayList<String> cat_id;
    ArrayList<String> menu_ids;

    public CartNumber() {
    }

    public CartNumber(ArrayList<String> cat_id, ArrayList<String> menu_ids) {
        this.cat_id = cat_id;
        this.menu_ids = menu_ids;
    }

    public ArrayList<String> getMenu_ids() {
        return menu_ids;
    }

    public void setMenu_ids(ArrayList<String> menu_ids) {
        this.menu_ids = menu_ids;
    }

    public ArrayList<String> getCat_id() {
        return cat_id;
    }

    public void setCat_id(ArrayList<String> cat_id) {
        this.cat_id = cat_id;
    }
}
