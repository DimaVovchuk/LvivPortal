package com.lab.epam.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 23.06.2015.
 */
public class WayPlaceImage {

    private Integer id;
    private String imageReference;
    private String name;
    private Date beginDate;

    public WayPlaceImage(Integer id, String imageReference, Date beginDate, String name, Date endDate, List<PlaceDescription> place) {
        this.id = id;
        this.imageReference = imageReference;
        this.beginDate = beginDate;
        this.name = name;
        this.endDate = endDate;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    private Date endDate;

    public Map<Integer, List<PlaceDescription>> getPlace() {
        return place;
    }

    public void setPlace(Map<Integer, List<PlaceDescription>> place) {
        this.place = place;
    }

    public WayPlaceImage(Integer id, String imageReference, String name, Date beginDate, Date endDate, Map<Integer, List<PlaceDescription>> place) {

        this.id = id;
        this.imageReference = imageReference;
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.place = place;
    }

    private Map<Integer, List<PlaceDescription>> place;

    public WayPlaceImage() {
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

}
