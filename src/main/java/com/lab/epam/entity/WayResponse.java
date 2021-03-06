package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("way_response")
public class WayResponse implements Identified<Integer> {

    @Column("id")
    private Integer id = null;
    @Column("description")
    private String description;
    @Column("rating")
    private Integer rating;
    @Column("user_id")
    private Integer user_id;
    @Column("way_id")
    private Integer way_id;
    @Column("deleted")
    private Boolean deleted = false;

    public WayResponse(String description, Integer rating,  Integer user_id, Integer way_id) {
        this.rating = rating;
        this.description = description;
        this.user_id = user_id;
        this.way_id = way_id;
    }

    public WayResponse(){
    }

    @Override
    public String toString() {
        return "PlaceResponse{" +
                "id=" + id +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", user_id='" + user_id + '\'' +
                ", way_id='" + way_id + '\'' +
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

    public Integer getWayID() {
        return way_id;
    }

    public void setWayID(Integer place_id) {
        this.way_id = way_id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
