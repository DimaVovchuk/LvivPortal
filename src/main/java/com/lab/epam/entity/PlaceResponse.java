package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("place_response")
public class PlaceResponse implements Identified<Integer> {

    @Column("id")
    private Integer id = null;
    @Column("description")
    private String description;
    @Column("user_id")
    private Integer user_id;
    @Column("place_id")
    private Integer place_id;
    @Column("deleted")
    private Boolean deleted = false;

    public PlaceResponse(String description,  Integer user_id, Integer place_id) {
        this.description = description;
        this.user_id = user_id;
        this.place_id = place_id;
    }

    public PlaceResponse(){
    }

    @Override
    public String toString() {
        return "PlaceResponse{" +
                "id=" + id +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
