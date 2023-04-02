package com.app.partyplanets.Model;

public class HappeningsModel {
    int image;
    String description;
    String title;

    public HappeningsModel(int image, String description,String title) {
        this.image = image;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HappeningsModel() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
