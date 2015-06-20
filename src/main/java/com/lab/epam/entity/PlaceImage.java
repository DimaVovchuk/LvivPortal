package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 12.06.2015.
 */
@Table("place_image")
public class PlaceImage implements Identified<Integer> {

    @Column("id")
    private Integer id = null;
    @Column("place_id")
    private Integer place_id;
    @Column("reference")
    private String reference;
    @Column("deleted")
    private Boolean deleted = false;

    public PlaceImage(Integer place_id, String reference){
        this.reference = reference;
        this.place_id = place_id;
    }

    public PlaceImage(){
    }

    @Override
    public String toString() {
        return "PlaceImage{" +
                "id=" + id +
                ", place_id=" + place_id +
                ", reference=" + reference +
                ", deleted='" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;

    }


}
