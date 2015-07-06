package com.lab.epam.entity;

/**
 * Created by Admin on 05.07.2015.
 */
public class UserImageDescription {
    private String reference;
    private String login;
    private String description;

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
