package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RoomData
{
    public int slected_room;
    public String id;
    public String room_type;
    public String room_size;
    public String price;
    public String offer_price;
    public Object image;
    public String availability;
    public String bed_type;
    public String max_adult;
    public String max_child;
    public Object totalroomsno;
    public String ac_type;
    public String extra_bed_charge;
    public String facilities;
    public String services;
    public String about_us;
    public String status;
    public String is_delete;
    public String listing_id;
    public String created_at;
    public int total_rooms;
    public String check_in;
    public String check_out;
    @SerializedName("galleryimages")
    @Expose
    ArrayList<Galleryimages> galleryimages;

    public ArrayList<Galleryimages> getGalleryimages() {
        return galleryimages;
    }

    public void setGalleryimages(ArrayList<Galleryimages> galleryimages)
    {
        this.galleryimages = galleryimages;
    }

    @SerializedName("amenities")
    @Expose
    public ArrayList<Amenities> amenities;

    public ArrayList<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<Amenities> amenities) {
        this.amenities = amenities;
    }

    public int getSlected_room() {
        return slected_room;
    }

    public void setSlected_room(int slected_room) {
        this.slected_room = slected_room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRoom_size() {
        return room_size;
    }

    public void setRoom_size(String room_size) {
        this.room_size = room_size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBed_type() {
        return bed_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public String getMax_adult() {
        return max_adult;
    }

    public void setMax_adult(String max_adult) {
        this.max_adult = max_adult;
    }

    public String getMax_child() {
        return max_child;
    }

    public void setMax_child(String max_child) {
        this.max_child = max_child;
    }

    public Object getTotalroomsno() {
        return totalroomsno;
    }

    public void setTotalroomsno(Object totalroomsno) {
        this.totalroomsno = totalroomsno;
    }

    public String getAc_type() {
        return ac_type;
    }

    public void setAc_type(String ac_type) {
        this.ac_type = ac_type;
    }

    public String getExtra_bed_charge() {
        return extra_bed_charge;
    }

    public void setExtra_bed_charge(String extra_bed_charge) {
        this.extra_bed_charge = extra_bed_charge;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getTotal_rooms() {
        return total_rooms;
    }

    public void setTotal_rooms(int total_rooms) {
        this.total_rooms = total_rooms;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }
}
