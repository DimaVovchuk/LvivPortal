package com.lab.epam.entity;

import java.util.List;

/**
 * Created by Admin on 26.06.2015.
 */
public class CategoryListPlace {


    String category;
    List<PlaceDescriptionAndPhoto> places;

    public CategoryListPlace(String category, List<PlaceDescriptionAndPhoto> places) {
        this.category = category;
        this.places = places;
    }

    public List<PlaceDescriptionAndPhoto> getPlaces() {

        return places;
    }

    public void setPlaces(List<PlaceDescriptionAndPhoto> places) {
        this.places = places;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryListPlace() {
    }


}
