package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Galleryimages
{
    public String id;
    public String image;
    public String room_id;
    public String status;
    public String created_at;
    @SerializedName("galleryimages")
    @Expose
    public ArrayList<Galleryimages> galleryimages;

    @SerializedName("amenities")
    @Expose
    ArrayList<Amenities> amenities;

    public ArrayList<Galleryimages> getGalleryimages() {
        return galleryimages;
    }

    public void setGalleryimages(ArrayList<Galleryimages> galleryimages) {
        this.galleryimages = galleryimages;
    }

    public ArrayList<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<Amenities> amenities) {
        this.amenities = amenities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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
}
