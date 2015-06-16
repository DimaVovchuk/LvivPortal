package com.lab.epam.entity;

/**
 * Created by Dima on 16-Jun-15.
 */
public class PlaceMarkerWithPhoto {

    private Integer placeId;
    private String name;
    private String latitude;
    private String longitude;
    private String imageReference;
    private String description;

    public PlaceMarkerWithPhoto(Integer placeId,String name,String latitude,String longitude,String imageReference,String description) {
        this.placeId = placeId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageReference = imageReference;
        this.description = description;

    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
}
