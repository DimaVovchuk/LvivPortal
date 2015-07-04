package com.lab.epam.entity;

/**
 * Created by Vasyl on 03.07.2015.
 */
public class CompanyGuideImage {
    private Integer id = null;
    private Integer rating;
    private String name;
    private String surname;
    private String login;
    private String mail;
    private String password;
    private String phone;
    private Integer status;
    private Integer role_id;
    private String about;
    private Integer avatar;
    private Boolean deleted = false;
    private String companyName;
    private String vk_id = null;
    private String reference;

    public CompanyGuideImage(Integer id, Integer rating, String name, String surname, String login, String mail, String password, String phone, Integer status, Integer role_id, String about, Integer avatar, Boolean deleted, String companyName, String vk_id, String reference) {
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
        this.about = about;
        this.avatar = avatar;
        this.deleted = deleted;
        this.companyName = companyName;
        this.vk_id = vk_id;
        this.reference = reference;
    }

    public CompanyGuideImage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVk_id() {
        return vk_id;
    }

    public void setVk_id(String vk_id) {
        this.vk_id = vk_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "CompanyGuideImage{" +
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
                ", companyName='" + companyName + '\'' +
                ", vk_id='" + vk_id + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
