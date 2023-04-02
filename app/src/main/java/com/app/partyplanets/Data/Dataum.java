package com.app.partyplanets.Data;

import com.app.partyplanets.Model.list;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Dataum
{
    String module_slug;
    String id;
    String name;
    String slug;
    String banner_img;
    String banner_img_title;
    String icon;
    public String vendor_id;
    public String veg_menu;
    public String non_veg_menu;
    public String module_id;


    public String getVeg_menu() {
        return veg_menu;
    }

    public void setVeg_menu(String veg_menu) {
        this.veg_menu = veg_menu;
    }

    public String getNon_veg_menu() {
        return non_veg_menu;
    }

    public void setNon_veg_menu(String non_veg_menu) {
        this.non_veg_menu = non_veg_menu;
    }

    public String phone_no;
    public String email_id;
    public String address;
    public String country;
    public String state;
    public String city;
    public String zip_code;
    public String latitude;
    public String longitude;
    public String seat_capacity;
    public String availability;
    public String rest_space;
    public String long_description;
    public String catering_policy;
    public String decor_policy;
    public String outside_alcohol;
    public String dj_policy;
    public String status;
    public String is_delete;
    public String created_at;
    public String offer_price;
    public String current_price;
    public String renovation_and_closures_policy;
    public String parking_policy;
    public String   health_and_hygiene;
    public Object languages;
    @SerializedName("roomdetails")
    @Expose
    private ArrayList<RoomPreViewModel> roomData;
    @SerializedName("amenities")
    @Expose
    ArrayList<Amenities> amenities;
    public ArrayList<RoomPreViewModel> getRoomData() {
        return roomData;
    }

    public String getModule_slug() {
        return module_slug;
    }

    public void setModule_slug(String module_slug) {
        this.module_slug = module_slug;
    }

    public void setRoomData(ArrayList<RoomPreViewModel> roomData) {
        this.roomData = roomData;
    }
    public ArrayList<Amenities> getAmenities()
    {
        return amenities;
    }

    public void setAmenities(ArrayList<Amenities> amenities) {
        this.amenities = amenities;
    }

    public String getHealth_and_hygiene() {
        return health_and_hygiene;
    }

    public void setHealth_and_hygiene(String health_and_hygiene) {
        this.health_and_hygiene = health_and_hygiene;
    }
    @SerializedName("gallery_images")
    @Expose
    ArrayList<Galleryimages> galleryimages;

    public ArrayList<Galleryimages> getGalleryimages() {
        return galleryimages;
    }

    public void setGalleryimages(ArrayList<Galleryimages> galleryimages) {
        this.galleryimages = galleryimages;
    }

    @SerializedName("list")
    @Expose
    ArrayList<list> list;

    public ArrayList<list> getList() {
        return list;
    }

    public void setList(ArrayList<list> list)
    {
        this.list = list;
    }

    public Dataum(String id, String name, String slug, String banner_img, String banner_img_title) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.banner_img = banner_img;
        this.banner_img_title = banner_img_title;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getRenovation_and_closures_policy() {
        return renovation_and_closures_policy;
    }

    public void setRenovation_and_closures_policy(String renovation_and_closures_policy) {
        this.renovation_and_closures_policy = renovation_and_closures_policy;
    }

   public String getParking_policy() {
        return parking_policy;
    }

    public void setParking_policy(String parking_policy) {
        this.parking_policy = parking_policy;
    }

    public Object getLanguages() {
        return languages;
    }

    public void setLanguages(Object languages) {
        this.languages = languages;
    }

    public String getIcon() {
        return icon;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSeat_capacity() {
        return seat_capacity;
    }

    public void setSeat_capacity(String seat_capacity) {
        this.seat_capacity = seat_capacity;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRest_space() {
        return rest_space;
    }

    public void setRest_space(String rest_space) {
        this.rest_space = rest_space;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getCatering_policy() {
        return catering_policy;
    }

    public void setCatering_policy(String catering_policy) {
        this.catering_policy = catering_policy;
    }

    public String getDecor_policy() {
        return decor_policy;
    }

    public void setDecor_policy(String decor_policy) {
        this.decor_policy = decor_policy;
    }

    public String getOutside_alcohol() {
        return outside_alcohol;
    }

    public void setOutside_alcohol(String outside_alcohol) {
        this.outside_alcohol = outside_alcohol;
    }

    public String getDj_policy() {
        return dj_policy;
    }

    public void setDj_policy(String dj_policy) {
        this.dj_policy = dj_policy;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getBanner_img_title() {
        return banner_img_title;
    }

    public void setBanner_img_title(String banner_img_title) {
        this.banner_img_title = banner_img_title;
    }
}
