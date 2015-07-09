package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 08.07.2015.
 */
@Table("image_response")
public class ImageResponse implements Identified<Integer> {

    public ImageResponse() {
    }

    public ImageResponse(Integer id, String description, Integer user_id, Integer image_id, Boolean deleted) {

        this.id = id;
        this.description = description;
        this.user_id = user_id;
        this.image_id = image_id;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                ", image_id=" + image_id +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Column("id")
    private Integer id = null;
    @Column("description")
    private String description;
    @Column("user_id")
    private Integer user_id;
    @Column("image_id")
    private Integer image_id;
    @Column("deleted")
    private Boolean deleted = false;



}

