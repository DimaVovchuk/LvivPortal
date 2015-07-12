package com.lab.epam.entity;

/**
 * Created by Admin on 05.07.2015.
 */
public class UserImageDescription {
    private String reference;
    private String login;
    private String description;


    public Integer getResponse_id() {
        return response_id;
    }

    public void setResponse_id(Integer response_id) {
        this.response_id = response_id;
    }

    public UserImageDescription(String reference, String login, String description, Integer response_id) {

        this.reference = reference;
        this.login = login;
        this.description = description;
        this.response_id = response_id;
    }

    private Integer response_id;
    private Integer place_id;

    public UserImageDescription(String reference, String description, String login, Integer response_id, Integer place_id) {
        this.reference = reference;
        this.description = description;
        this.login = login;
        this.response_id = response_id;
        this.place_id = place_id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserImageDescription() {

    }

    public UserImageDescription(String reference, String login, String description) {

        this.reference = reference;
        this.login = login;
        this.description = description;
    }
}
