package com.lab.epam.entity;

import com.lab.epam.transformer.Column;

/**
 * Created by Vasyl on 05.07.2015.
 */
public class FavoritePlacesByRating {
    @Column("id")
    private Integer id = null;
    @Column("user_id")
    private Integer user_id;
    @Column("place_id")
    private Integer place_id;
    @Column("deleted")
    private Boolean deleted = false;
    private Integer count;

    public FavoritePlacesByRating() {
    }

    public FavoritePlacesByRating(Integer id, Integer user_id, Integer place_id, Boolean deleted, Integer count) {
        this.id = id;
        this.user_id = user_id;
        this.place_id = place_id;
        this.deleted = deleted;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "FavoritePlacesByRating{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", place_id=" + place_id +
                ", deleted=" + deleted +
                ", count=" + count +
                '}';
    }
}
