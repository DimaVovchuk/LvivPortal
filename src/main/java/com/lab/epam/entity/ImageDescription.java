package com.lab.epam.entity;

/**
 * Created by Admin on 24.06.2015.
 */
public class ImageDescription {

    private String imageReference;
    private Integer place_id;
    private String locale;
    private String name;
    private String description;
    private String phone;

    public ImageDescription() {
    }

    public ImageDescription(String imageReference, Integer place_id, String locale, String name, String description, String phone, String price) {

        this.imageReference = imageReference;
        this.place_id = place_id;
        this.locale = locale;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ImageDescription{" +
                "imageReference='" + imageReference + '\'' +
                ", place_id=" + place_id +
                ", locale='" + locale + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

}
