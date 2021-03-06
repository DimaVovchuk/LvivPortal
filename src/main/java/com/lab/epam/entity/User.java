package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Oleguk on 09.06.2015.
 */
@Table("user")
public class User implements Identified<Integer> {

    @Column("id")
    private Integer id = null;
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
    @Column("status_id")
    private Integer status;
    @Column("role_id")
    private Integer role_id;
    @Column("about")
    private String about;
    @Column("avatar_id")
    private Integer avatar;
    @Column("deleted")
    private Boolean deleted = false;
    @Column("company_name")
    private String companyName;
    @Column("vk_id")
    private String vk_id = null;

    public User(Builder builder) {

        rating = builder.rating;
        name = builder.name;
        surname = builder.surname;
        login = builder.login;
        mail = builder.mail;
        password = builder.password;
        phone = builder.phone;
        status = builder.status;
        role_id = builder.role_id;
        about = builder.about;
        avatar = builder.avatar;
    }

    public User(){
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
                ", status=" + status +
                ", role_id=" + role_id +
                ", about='" + about + '\'' +
                ", avatar=" + avatar +
                ", deleted=" + deleted +
                ", companyName=" + companyName +
                ", vk_id=" + vk_id +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getVkId() {
        return vk_id;
    }

    public void setVkId(String vk_id) {
        this.vk_id = vk_id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getAbout() {

        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public static class Builder {
        private String name;
        private String surname;
        private String login;
        private String mail;
        private String password;
        private String phone;
        private Integer role_id;

        private Integer rating;
        private Integer status;
        private String about;
        private Integer avatar;

        public Builder(String name, String surname, String login, String mail, String password, String phone, Integer role_id) {
            this.name = name;
            this.surname = surname;
            this.login = login;
            this.mail = mail;
            this.password = password;
            this.phone = phone;
            this.role_id = role_id;
        }

        public Builder rating(int val) {
            rating = val;
            return this;
        }

        public Builder status(int val) {
            status = val;
            return this;
        }

        public Builder avatar(Integer val) {
            avatar = val;
            return this;
        }

        public Builder about(String val) {
            about = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


}
