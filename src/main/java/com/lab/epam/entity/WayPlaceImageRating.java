package com.lab.epam.entity;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 01.07.2015.
 */
public class WayPlaceImageRating {

    private Integer id;
    private String imageReference;
    private Date beginDate;
    private Date endDate;
    //private List<PlaceDescription> place;
    private Integer rating;
    private Integer rating_way;
    public Map<Integer, List<PlaceDescription>> place;



    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public WayPlaceImageRating() {
    }

    public Map<Integer, List<PlaceDescription>> getPlace() {
        return place;
    }

    public void setPlace(Map<Integer, List<PlaceDescription>> place) {
        this.place = place;
    }

    public WayPlaceImageRating(Integer id, String imageReference, Date beginDate, Date endDate, Integer rating, Integer rating_way, Map<Integer, List<PlaceDescription>> place, String name) {

        this.id = id;
        this.imageReference = imageReference;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.rating = rating;
        this.rating_way = rating_way;
        this.place = place;
        this.name = name;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getRating_way() {
        return rating_way;
    }

    public void setRating_way(Integer rating_way) {
        this.rating_way = rating_way;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public static class WayRatingComparator implements Comparator<Object> {
        public int compare(Object cC1, Object cC2) {
            return ((WayPlaceImageRating)cC2).rating_way.compareTo(((WayPlaceImageRating)cC1).rating_way);
        }
    }
}
