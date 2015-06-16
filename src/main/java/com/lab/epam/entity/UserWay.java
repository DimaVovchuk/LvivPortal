package com.lab.epam.entity;

/**
 * Created by Vasyl on 16.06.2015.
 */

public class UserWay {
    private Integer wayId;
    private String wayName;
    private Integer wayDays;
    private Integer wayTime;
    private String address;
    private String latitude;
    private String longitude;
    private Integer categoryId;
    private Integer rating;
    private Integer placeTime;
    private String reference;
    private String description;
    private String placeName;

    public UserWay() {
    }

    public UserWay(Integer wayId, String wayName, Integer wayDays, Integer wayTime, String address, String latitude, String longitude, Integer categoryId, Integer rating, Integer placeTime, String reference, String description, String placeName) {
        this.wayId = wayId;
        this.wayName = wayName;
        this.wayDays = wayDays;
        this.wayTime = wayTime;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryId = categoryId;
        this.rating = rating;
        this.placeTime = placeTime;
        this.reference = reference;
        this.description = description;
        this.placeName = placeName;
    }

    public Integer getWayId() {
        return wayId;
    }

    public void setWayId(Integer wayId) {
        this.wayId = wayId;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public Integer getWayDays() {
        return wayDays;
    }

    public void setWayDays(Integer wayDays) {
        this.wayDays = wayDays;
    }

    public Integer getWayTime() {
        return wayTime;
    }

    public void setWayTime(Integer wayTime) {
        this.wayTime = wayTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Integer placeTime) {
        this.placeTime = placeTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
