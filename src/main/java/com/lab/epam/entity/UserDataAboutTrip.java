package com.lab.epam.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 18.06.2015.
 */
public class UserDataAboutTrip {

    private Integer dayCount = 1;

    public Integer getWay_id() {
        return way_id;
    }

    public void setWay_id(Integer way_id) {
        this.way_id = way_id;
    }

    private Integer way_id = 0;
    private String beginPlace;
    private String endPlace;
    private Date beginTrip;
    private Date endTrip;
    private Boolean dontKnowDate = false;
    private Boolean withOutBegin = false;
    private List<Category> category;
    private Boolean isCaffees = false;
    private Map<Integer,List<Place>> placeDay = new HashMap<>();
    private double routeTime;
    private Boolean isAutomatic = false;
    private Double timePerDay;
    private Double timeForLunch;
    private boolean sortFlag = true;


    public Boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(Boolean isFull) {
        this.isFull = isFull;
    }

    private Boolean isFull = false;

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    private Boolean isSaved = false;

    public Boolean getIsAutomatic() {
        return isAutomatic;
    }

    public void setIsAutomatic(Boolean isAutomatic) {
        this.isAutomatic = isAutomatic;
    }



    public Boolean getDontKnowDate() {
        return dontKnowDate;
    }

    public void setDontKnowDate(Boolean dontKnowDate) {
        this.dontKnowDate = dontKnowDate;
    }

    public Boolean getWithOutBegin() {
        return withOutBegin;
    }

    public void setWithOutBegin(Boolean withOutBegin) {
        this.withOutBegin = withOutBegin;
    }

    public UserDataAboutTrip() {

    }

    public Date getBeginTrip() {
        return beginTrip;
    }

    public void setBeginTrip(Date beginTrip) {
        this.beginTrip = beginTrip;
    }

    public Date getEndTrip() {
        return endTrip;
    }

    public void setEndTrip(Date endTrip) {
        this.endTrip = endTrip;
    }

    public Integer getDayCount() {

        return dayCount;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public Boolean getIsCaffees() {
        return isCaffees;
    }

    public void setIsCaffees(Boolean isCaffees) {
        this.isCaffees = isCaffees;
    }

    public Map<Integer, List<Place>> getPlaceDay() {
        return placeDay;
    }

    public void setPlaceDay(Map<Integer, List<Place>> placeDay) {
        this.placeDay = placeDay;
    }

    public String getBeginPlace() {

        return beginPlace;
    }

    public void setBeginPlace(String beginPlace) {
        this.beginPlace = beginPlace;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }


    @Override
    public String toString() {
        return "UserDataAboutTrip{" +
                "dayCount=" + dayCount +
                ", beginPlace='" + beginPlace + '\'' +
                ", endPlace='" + endPlace + '\'' +
                ", beginTrip=" + beginTrip +
                ", endTrip=" + endTrip +
                ", dontKnowDate=" + dontKnowDate +
                ", withOutBegin=" + withOutBegin +
                ", category=" + category +
                ", isCaffees=" + isCaffees +
                ", placeDay=" + placeDay +
                ", isAutomatic=" + isAutomatic +
                '}';
    }

    public Double getTimeForLunch() {
        return timeForLunch;
    }

    public void setTimeForLunch(Double timeForLunch) {
        this.timeForLunch = timeForLunch;
    }

    public Double getTimePerDay() {
        return timePerDay;
    }

    public void setTimePerDay(Double timePerDay) {
        this.timePerDay = timePerDay;
    }

    public boolean getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(boolean sortFlag) {
        this.sortFlag = sortFlag;
    }

    public double getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(double routeTime) {
        this.routeTime = routeTime;
    }
}
