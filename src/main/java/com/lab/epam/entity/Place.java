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
    @Column("adress")
    private String adress;
    @Column("latitude")
    private String latitude;
    @Column("longitude")
    private String longitude;
    @Column("visible")
    private Boolean visible;
    @Column("rating")
    private Integer rating;
    @Column("category_id")
    private Integer category_id;
    @Column("place_time")
    private Integer place_time;
    @Column("deleted")
    private Boolean deleted = false;

    public Place(String adress, String latitude, String longitude, Integer category_id, Integer rating,  Boolean visible, Integer place_time) {
        this.rating = rating;
        this.adress = adress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visible = visible;
        this.category_id = category_id;
        this.place_time = place_time;
    }

    public Place(){
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", category_id='" + category_id + '\'' +
                ", rating=" + rating +
                ", visible='" + visible + '\'' +
                ", place_time='" + place_time + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getPlace_time() {
        return place_time;
    }

    public void setPlace_time(Integer place_time) {
        this.place_time = place_time;
    }


   public static class PlaceComparator implements Comparator<Object> {
        public int compare(Object cC1, Object cC2) {
            return ((Place)cC1).rating.compareTo(((Place)cC2).rating);
        }
    }

    public boolean equals(Object place){
        Place places = (Place)place;
        if (this.getId() == places.getId()){
            return true;
        } else return false;
    }

}


