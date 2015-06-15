package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("way")
public class Way implements Identified<Integer> {
    @Column("id")
    private Integer id = null;
    @Column("rating")
    private Integer rating;
    @Column("name")
    private String name;
    @Column("way_days")
    private Integer way_days;
    @Column("way_time")
    private Integer way_time;
    @Column("visible")
    private Boolean visible;
    @Column("deleted")
    private Boolean deleted = false;

    public Way(Integer rating, String name, Boolean visible, Integer way_days, Integer way_time){
        this.rating = rating;
        this.name = name;
        this.visible = visible;
        this.way_days = way_days;
        this.way_time = way_time;
    }

    public Way(){
    }

    @Override
    public String toString() {
        return "Way{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", visible='" + visible + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
