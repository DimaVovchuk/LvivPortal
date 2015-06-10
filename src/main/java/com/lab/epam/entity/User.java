package com.lab.epam.entity;

import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Oleguk on 09.06.2015.
 */
@Table("user")
public class User {

    @Column("id")
    private Integer id;
    @Column("rating")
    private Integer rating;
    @Column("name")
    private String name;
    @Column("surname")
    private String surname;
    @Column("login")
    private String login;
    @Column("mail")
    private String mail;
    @Column("password")
    private String password;
    @Column("phone")
    private String phone;
    @Column("status")
    private String status;
    @Column("role_id")
    private Integer role_id;
    @Column("deleted")
    private Boolean deleted;

    public User(Integer id, Integer rating, String name, String surname, String login, String mail, String password, String phone, String status, Integer role_id, Boolean deleted) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.role_id = role_id;
        this.deleted = deleted;
    }

    public User(){
        this.id = null;
        this.rating = null;
        this.name = null;
        this.surname = null;
        this.login = null;
        this.mail = null;
        this.password = null;
        this.phone = null;
        this.status = null;
        this.role_id = null;
        this.deleted = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role_id + '\'' +
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoleID() {
        return role_id;
    }

    public void setRoleID(Integer role_id) {
        this.role_id = role_id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
