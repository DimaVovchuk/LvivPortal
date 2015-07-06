package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

import java.util.Comparator;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("place")
public class Place implements Identified<Integer> {
    @Column("id")
    private Integer id = null;
    @Column("latitude")
    private String latitude;
    @Column("longitude")
    private String longitude;
    @Column("visible")
    private Boolean visible = true;
    @Column("rating")
    private Integer rating;
    @Column("category_id")
    private Integer category_id;
    @Column("place_time")
    private Integer place_time;
    @Column("recomended")
    private Boolean recomended;
    @Column("recom_time")
    private Integer recom_time;
    @Column("custom")
    private Boolean custom;
    @Column("is_recommended")
    private Boolean is_recommended;
    @Column("deleted")
    private Boolean deleted = false;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_recommended() {
        return is_recommended;
    }

    public void setIs_recommended(Boolean is_recommended) {
        this.is_recommended = is_recommended;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    public Integer getRecom_time() {
        return recom_time;
    }

    public void setRecom_time(Integer recom_time) {
        this.recom_time = recom_time;
    }

    public Boolean getRecomended() {
        return recomended;
    }

    public void setRecomended(Boolean recomended) {
        this.recomended = recomended;
    }

    public Integer getPlace_time() {
        return place_time;
    }

    public void setPlace_time(Integer place_time) {
        this.place_time = place_time;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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

    public Place() {

    }

    public Place(Integer id, String latitude, String longitude, Boolean visible, Integer category_id, Integer rating, Integer place_time, Boolean recomended, Boolean custom, Boolean deleted, Integer recom_time, Boolean is_recommended) {

        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visible = visible;
        this.category_id = category_id;
        this.rating = rating;
        this.place_time = place_time;
        this.recomended = recomended;
        this.custom = custom;
        this.deleted = deleted;
        this.recom_time = recom_time;
        this.is_recommended = is_recommended;
    }

    public static class PlaceComparator implements Comparator<Object> {
        public int compare(Object cC1, Object cC2) {
            return ((Place)cC2).rating.compareTo(((Place)cC1).rating);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        return getId().equals(place.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", visible=" + visible +
                ", rating=" + rating +
                ", category_id=" + category_id +
                ", place_time=" + place_time +
                ", recomended=" + recomended +
                ", custom=" + custom +
                ", deleted=" + deleted +
                '}';
    }
}


