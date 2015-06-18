package com.lab.epam.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 18.06.2015.
 */
public class UserDataAboutTrip {

    private Integer dayCount;
    private Integer hoursCount;
    private String beginTrip;
    private String endTrip;
    private List<Category> category;
    private Boolean isHotel = true;
    private Boolean isCaffees = true;
    private Integer timeBetweenCaffees;
    private Map<Integer,List<Place>> placeDay = new HashMap<>();

    public UserDataAboutTrip(Builder builder) {

        dayCount = builder.dayCount;
        hoursCount = builder.hoursCount;
        beginTrip = builder.beginTrip;
        endTrip = builder.endTrip;
        category = builder.category;
        isHotel = builder.isHotel;
        isCaffees = builder.isCaffees;
        timeBetweenCaffees = builder.timeBetweenCaffees;
        placeDay = builder.placeDay;
    }

    public UserDataAboutTrip(){
    }

    public Integer getHoursCount() {
        return hoursCount;
    }

    public void setHoursCount(Integer hoursCount) {
        this.hoursCount = hoursCount;
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

    public String getEndTrip() {
        return endTrip;
    }

    public void setEndTrip(String endTrip) {
        this.endTrip = endTrip;
    }

    public Boolean getIsHotel() {
        return isHotel;
    }

    public void setIsHotel(Boolean isHotel) {
        this.isHotel = isHotel;
    }

    public Boolean getIsCaffees() {
        return isCaffees;
    }

    public void setIsCaffees(Boolean isCaffees) {
        this.isCaffees = isCaffees;
    }

    public Integer getTimeBetweenCaffees() {
        return timeBetweenCaffees;
    }

    public void setTimeBetweenCaffees(Integer timeBetweenCaffees) {
        this.timeBetweenCaffees = timeBetweenCaffees;
    }

    public Map<Integer, List<Place>> getPlaceDay() {
        return placeDay;
    }

    public void setPlaceDay(Map<Integer, List<Place>> placeDay) {
        this.placeDay = placeDay;
    }

    public String getBeginTrip() {

        return beginTrip;
    }

    public void setBeginTrip(String beginTrip) {
        this.beginTrip = beginTrip;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public static class Builder {
        private Integer dayCount;
        private Integer hoursCount;
        private List<Category> category;

        private String beginTrip;
        private String endTrip;
        private Boolean isHotel = true;
        private Boolean isCaffees = true;
        private Integer timeBetweenCaffees;
        private Map<Integer,List<Place>> placeDay = new HashMap<>();

        public Builder(Integer dayCount, Integer hoursCount, List<Category> category) {
            this.dayCount = dayCount;
            this.category = category;
            this.hoursCount = hoursCount;
        }

        public Builder beginTrip(String val) {
            beginTrip = val;
            return this;
        }

        public Builder endTrip(String val) {
            endTrip = val;
            return this;
        }

        public Builder beginTrip(Boolean val) {
            isHotel = val;
            return this;
        }

        public Builder endTrip(Boolean val) {
            isHotel = val;
            return this;
        }

        public Builder timeBetweenCaffees(Integer val) {
            timeBetweenCaffees = val;
            return this;
        }

        public Builder placeDay(Map<Integer,List<Place>> val) {
            placeDay = val;
            return this;
        }

        public UserDataAboutTrip build() {
            return new UserDataAboutTrip(this);
        }
    }

}
