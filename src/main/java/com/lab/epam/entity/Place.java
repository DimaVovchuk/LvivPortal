package com.lab.epam.entity;

import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("place")
public class Place {
    @Column("id")
    private Integer id;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("adress")
    private String adress;
    @Column("latitude")
    private String latitude;
    @Column("longitude")
    private String longitude;
    @Column("visible")
    private String visible;
    @Column("rating")
    private Integer rating;
    @Column("category_id")
    private Integer category_id;
    @Column("deleted")
    private Boolean deleted;

    public Place(Integer id,String name, String description, String adress, String latitude, String longitude, Integer rating,  String visible, Integer category_id, Boolean deleted) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visible = visible;
        this.category_id = category_id;
        this.deleted = deleted;
    }

    public Place(){
        this.id = null;
        this.rating = null;
        this.name = null;
        this.description = null;
        this.adress = null;
        this.latitude = null;
        this.longitude = null;
        this.visible = null;
        this.category_id = null;
        this.deleted = null;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adress='" + adress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", category_id='" + category_id + '\'' +
                ", rating=" + rating +
                ", visible='" + visible + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getCategoryID() {
        return category_id;
    }

    public void setCategoryID(Integer category_id) {
        this.category_id = category_id;
    }


    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}


