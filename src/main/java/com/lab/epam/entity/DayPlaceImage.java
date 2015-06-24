package com.lab.epam.entity;

import java.util.List;

/**
 * Created by Admin on 24.06.2015.
 */
public class DayPlaceImage {

    private Integer day;

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    private String imageReference;
    private List<ImageDescription> placeImage;

    public DayPlaceImage() {
    }

    public DayPlaceImage(Integer day, List<ImageDescription> placeImage) {

        this.day = day;
        this.placeImage = placeImage;
    }

    @Override
    public String toString() {
        return "DayPlaceImage{" +
                "day=" + day +
                ", placeImage=" + placeImage +
                '}';
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public List<ImageDescription> getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(List<ImageDescription> placeImage) {
        this.placeImage = placeImage;
    }
}

