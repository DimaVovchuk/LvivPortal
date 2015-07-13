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
    private Integer beginPlace;
    private Integer endPlace;
    private Date beginTrip;
    private Date endTrip;
    private Boolean dontKnowDate = false;
    private Boolean withOutBegin = false;

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public UserDataAboutTrip(Integer dayCount, Integer way_id, Integer beginPlace, Integer endPlace, Date beginTrip, Date endTrip, Boolean dontKnowDate, Boolean recommended, Boolean withOutBegin, List<Category> category, Boolean isCaffees, Map<Integer, List<Place>> placeDay, double routeTime, Boolean isAutomatic, Double timePerDay, Double timeForLunch, String name, Map<Integer, Boolean> sortFlag, Boolean isFull, Boolean isSaved) {

        this.dayCount = dayCount;
        this.way_id = way_id;
        this.beginPlace = beginPlace;
        this.endPlace = endPlace;
        this.beginTrip = beginTrip;
        this.endTrip = endTrip;
        this.dontKnowDate = dontKnowDate;
        this.recommended = recommended;
        this.withOutBegin = withOutBegin;
        this.category = category;
        this.isCaffees = isCaffees;
        this.placeDay = placeDay;
        this.routeTime = routeTime;
        this.isAutomatic = isAutomatic;
        this.timePerDay = timePerDay;
        this.timeForLunch = timeForLunch;
        this.name = name;
        this.sortFlag = sortFlag;
        this.isFull = isFull;
        this.isSaved = isSaved;
    }

    private Boolean recommended = false;
    private List<Category> category;
    private Boolean isCaffees = false;
    private Map<Integer,List<Place>> placeDay = new HashMap<>();
    private double routeTime;
    private Boolean isAutomatic = false;
    private Double timePerDay;
    private Double timeForLunch;
    private String name;
    private Map<Integer,Boolean> sortFlag = new HashMap<>();


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

    public Integer getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(Integer endPlace) {
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

    public Integer getBeginPlace() {

        return beginPlace;
    }

    public void setBeginPlace(Integer beginPlace) {
        this.beginPlace = beginPlace;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }


    @Override
    public String toString() {
        return "UserDataAboutTrip{" +
                "dayCount=" + dayCount +
                ", way_id=" + way_id +
                ", beginPlace=" + beginPlace +
                ", endPlace=" + endPlace +
                ", beginTrip=" + beginTrip +
                ", endTrip=" + endTrip +
                ", dontKnowDate=" + dontKnowDate +
                ", withOutBegin=" + withOutBegin +
                ", category=" + category +
                ", isCaffees=" + isCaffees +
                ", placeDay=" + placeDay +
                ", routeTime=" + routeTime +
                ", isAutomatic=" + isAutomatic +
                ", timePerDay=" + timePerDay +
                ", timeForLunch=" + timeForLunch +
                ", name='" + name + '\'' +
                ", sortFlag=" + sortFlag +
                ", isFull=" + isFull +
                ", isSaved=" + isSaved +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDataAboutTrip(Integer dayCount, Boolean isSaved, Boolean isFull, Map<Integer, Boolean> sortFlag, String name, Double timeForLunch, Double timePerDay, Boolean isAutomatic, double routeTime, Map<Integer, List<Place>> placeDay, Boolean isCaffees, List<Category> category, Boolean withOutBegin, Boolean dontKnowDate, Date endTrip, Date beginTrip, Integer endPlace, Integer beginPlace, Integer way_id) {
        this.dayCount = dayCount;
        this.isSaved = isSaved;
        this.isFull = isFull;
        this.sortFlag = sortFlag;
        this.name = name;
        this.timeForLunch = timeForLunch;
        this.timePerDay = timePerDay;
        this.isAutomatic = isAutomatic;
        this.routeTime = routeTime;
        this.placeDay = placeDay;
        this.isCaffees = isCaffees;
        this.category = category;
        this.withOutBegin = withOutBegin;
        this.dontKnowDate = dontKnowDate;
        this.endTrip = endTrip;
        this.beginTrip = beginTrip;
        this.endPlace = endPlace;
        this.beginPlace = beginPlace;
        this.way_id = way_id;
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

    public double getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(double routeTime) {
        this.routeTime = routeTime;
    }

    public Map<Integer, Boolean> getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Map<Integer, Boolean> sortFlag) {
        this.sortFlag = sortFlag;
    }
}
