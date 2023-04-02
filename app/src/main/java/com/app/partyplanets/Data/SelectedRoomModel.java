package com.app.partyplanets.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SelectedRoomModel {

    public int inserted_id;
    public String id;
    public String time_slot;
    public String person_no;
    public String plate_type;
    public String function_type;
    public String bookingAdultNo;
    public String bookingChildNo;

    public String vendor_id;
    public String module_id;
    public String slug;
    public String name;
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
    public String banner_img;
    public String long_description;
    public String catering_policy;
    public String offer_price;
    public String current_price;
    public String health_and_hygiene;
    public String area;
    public String renovation_and_closures_policy;
    public String parking_policy;
    public String languages;
    public String decor_policy;
    public String outside_alcohol;
    public String dj_policy;
    public String amenities;
    public String status;
    public String is_delete;
    public String created_at;
    public String updated_at;
    public String checkoutdate;
    public String module_name;
    public String payment_type;
//////////////////////////////////////////

    public String booking_id;

    public String getBookingAdultNo() {
        return bookingAdultNo;
    }

    public void setBookingAdultNo(String bookingAdultNo) {
        this.bookingAdultNo = bookingAdultNo;
    }

    public String getBookingChildNo() {
        return bookingChildNo;
    }

    public void setBookingChildNo(String bookingChildNo) {
        this.bookingChildNo = bookingChildNo;
    }

    public String vendor_food_menu_id;
    public String price;
    public String type;
    public String qty;
    public String total_amt;
    public String menu_id;
    public String title;
    public String sub_title;
    public String image;
    public String total_price;

    public String getFunction_type() {
        return function_type;
    }

    public void setFunction_type(String function_type) {
        this.function_type = function_type;
    }

    public String getPlate_type() {
        return plate_type;
    }

    public void setPlate_type(String plate_type) {
        this.plate_type = plate_type;
    }

    public String getPerson_no() {
        return person_no;
    }

    public void setPerson_no(String person_no) {
        this.person_no = person_no;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getTime_slot()
    {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getVendor_food_menu_id() {
        return vendor_food_menu_id;
    }

    public void setVendor_food_menu_id(String vendor_food_menu_id) {
        this.vendor_food_menu_id = vendor_food_menu_id;
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

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
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

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String checkindate;


    public int getInserted_id() {
        return inserted_id;
    }

    public void setInserted_id(int inserted_id) {
        this.inserted_id = inserted_id;
    }

    @SerializedName("roomdetails")
    @Expose
    private ArrayList<RoomPreViewModel> roomData;

    public ArrayList<RoomPreViewModel> getRoomData() {
        return roomData;
    }

    public void setRoomData(ArrayList<RoomPreViewModel> roomData) {
        this.roomData = roomData;
    }

    //  private ArrayList<RoomData> roomData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
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

    public String getHealth_and_hygiene() {
        return health_and_hygiene;
    }

    public void setHealth_and_hygiene(String health_and_hygiene) {
        this.health_and_hygiene = health_and_hygiene;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
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

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


}
