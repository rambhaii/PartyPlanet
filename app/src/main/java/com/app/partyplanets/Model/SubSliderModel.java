package com.app.partyplanets.Model;

public class SubSliderModel {
    int img;
    int Image;
    String description;
    String header;
    int rounddesign;

    public SubSliderModel(int img, int Image, String description, String header) {
        this.img = img;
        this.description=description;
        this.Image=Image;
        this.header=header;

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
