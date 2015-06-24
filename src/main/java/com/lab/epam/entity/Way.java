package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

import java.sql.Date;

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

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getWay_time() {
        return way_time;
    }

    public void setWay_time(Integer way_time) {
        this.way_time = way_time;
    }

    public Integer getWay_days() {
        return way_days;
    }

    public void setWay_days(Integer way_days) {
        this.way_days = way_days;
    }

    @Column("date_begin")

    private Date begin;
    @Column("date_end")
    private Date end;
    @Column("deleted")
    private Boolean deleted = false;

    public Way(Integer id, Integer rating, String name, Integer way_days, Integer way_time, Boolean visible, Date begin, Date end, Boolean deleted) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.way_days = way_days;
        this.way_time = way_time;
        this.visible = visible;
        this.begin = begin;
        this.end = end;
        this.deleted = deleted;
    }

    public Way(Integer rating, Date begin, Date end){
        this.rating = rating;
        this.begin = begin;
        this.end = end;
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
