package com.lab.epam.entity;

/**
 * Created by Admin on 08.07.2015.
 */
public class ImageResponseAvatar {

    private String avaterReference;
    private String description;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ImageResponseAvatar(String login, Integer id, String description, String avaterReference) {

        this.login = login;
        this.id = id;
        this.description = description;
        this.avaterReference = avaterReference;
    }

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
