package com.lab.epam.entity;

import com.lab.epam.dao.Identified;

/**
 * Created by Dima on 16-Jun-15.
 */
public class PlaceMarkerWithPhoto {

    private String name;
    private String latitude;
    private String longitude;
    private String imageReference;

    public PlaceMarkerWithPhoto(String name,String latitude,String longitude,String imageReference) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageReference = imageReference;
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
}
