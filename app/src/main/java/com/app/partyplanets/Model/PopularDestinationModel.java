package com.app.partyplanets.Model;

public class PopularDestinationModel {
    int image;
    String description;

    public PopularDestinationModel(int image, String description) {
        this.image = image;
        this.description = description;
    }

    public PopularDestinationModel() {
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
