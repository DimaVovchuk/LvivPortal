package com.lab.epam.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasyl on 09.07.2015.
 */
public class RouteOneDayPlacesInfo {
    private Integer dayNumber;
    private Integer totalMinutes;
    private Integer hours;
    private Integer minutes;
    private List<PlaceMarkerWithPhoto> places;

    public RouteOneDayPlacesInfo(Integer dayNumber) {
        this.dayNumber = dayNumber;
        this.totalMinutes = 0;
        places = new ArrayList<>();
    }

    public RouteOneDayPlacesInfo() {

    }

    public List<PlaceMarkerWithPhoto> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceMarkerWithPhoto> places) {
        this.places = places;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(Integer totalMinutes) {
        this.totalMinutes = totalMinutes;
        this.minutes = this.totalMinutes % 60;
        this.hours = (this.totalMinutes - this.minutes) / 60;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "RouteOneDayPlacesInfo{" +
                "dayNumber=" + dayNumber +
                ", totalMinutes=" + totalMinutes +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", places=" + places +
                '}';
    }
}
