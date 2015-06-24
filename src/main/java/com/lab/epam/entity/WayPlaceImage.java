package com.lab.epam.entity;

import java.util.List;

/**
 * Created by Admin on 23.06.2015.
 */
public class WayPlaceImage {

    private Integer id;
    private String imageReference;
    private List<PlaceDescription> place;

    public WayPlaceImage() {
    }

    public WayPlaceImage(Integer id, String imageReference, List<PlaceDescription> place) {

        this.id = id;
        this.imageReference = imageReference;
        this.place = place;
    }

    @Override
    public String toString() {
        return "WayPlaceImage{" +
                "id=" + id +
                ", imageReference='" + imageReference + '\'' +
                ", place=" + place +
                '}';
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PlaceDescription> getPlace() {
        return place;
    }

    public void setPlace(List<PlaceDescription> place) {
        this.place = place;
    }
}
