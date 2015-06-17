package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 15.06.2015.
 */

@Table("place_rating")
public class PlaceRating implements Identified<Integer> {

    @Column("id")
    private Integer id = null;
    @Column("user_id")
    private Integer user_id;
    @Column("place_id")
    private Integer place_id;
    @Column("rating")
    private Integer rating;
    @Column("deleted")
    private Boolean deleted = false;

    public PlaceRating(Integer user_id, Integer place_id, Integer rating) {
        this.rating = rating;
        this.user_id = user_id;
        this.place_id = place_id;
    }

    public PlaceRating(){
    }

    @Override
    public String toString() {
        return "PlaceResponse{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
