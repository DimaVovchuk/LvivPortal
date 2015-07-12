package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 13.06.2015.
 */
@Table("user_image")
public class UserImage implements Identified<Integer> {

    @Column("id")
    private Integer id = null;
    @Column("user_id")
    private Integer user_id;
    @Column("reference")
    private String reference;
    @Column("deleted")
    private Boolean deleted = false;

    public UserImage(Integer id, Integer user_id, String reference, Boolean deleted, String description) {
        this.id = id;
        this.user_id = user_id;
        this.reference = reference;
        this.deleted = deleted;
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column("description")
    private String description;

    public UserImage(Integer user_id, String reference){
        this.reference = reference;
        this.user_id = user_id;
    }

    public UserImage(){
    }

    @Override
    public String toString() {
        return "PlaceImage{" +
                "id=" + id +
                "user_id=" + user_id +
                ", reference=" + reference +
                ", deleted='" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String Place_id) {
        this.reference = reference;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;

    }


}

