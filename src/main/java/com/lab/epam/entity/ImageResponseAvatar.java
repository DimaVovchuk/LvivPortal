package com.lab.epam.entity;

/**
 * Created by Admin on 08.07.2015.
 */
public class ImageResponseAvatar {

    private String avaterReference;
    private String description;
    private Integer id;

    public String getAvaterReference() {
        return avaterReference;
    }

    public void setAvaterReference(String avaterReference) {
        this.avaterReference = avaterReference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImageResponseAvatar() {

    }

    public ImageResponseAvatar(String avaterReference, String description, Integer id) {

        this.avaterReference = avaterReference;
        this.description = description;
        this.id = id;
    }
}
