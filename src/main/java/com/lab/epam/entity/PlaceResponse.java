package com.lab.epam.entity;

/**
 * Created by Admin on 10.06.2015.
 */
public class PlaceResponse {

    private Integer id;
    private String description;
    private Integer rating;
    private Integer user_id;
    private Integer place_id;
    private Boolean deleted;

    public PlaceResponse(Integer id, String description, Integer rating,  Integer user_id, Integer place_id, Boolean deleted) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.user_id = user_id;
        this.place_id = place_id;
        this.deleted = deleted;
    }

    public PlaceResponse(){
        this.id = null;
        this.rating = null;
        this.description = null;
        this.user_id = null;
        this.place_id = null;
        this.deleted = null;
    }

    @Override
    public String toString() {
        return "PlaceResponse{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", user_id='" + user_id + '\'' +
                ", place_id='" + place_id + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserID() {
        return user_id;
    }

    public void setUserID(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPlaceID() {
        return place_id;
    }

    public void setPlaceID(Integer place_id) {
        this.place_id = place_id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
