package com.lab.epam.entity;

public class PlaceDescriptionAndPhoto {

    private Integer id;
    private String imageReference;
    private String name;
    private String adress;

    public PlaceDescriptionAndPhoto(Integer id, String imageReference, String name, String adress) {
        this.id = id;
        this.imageReference = imageReference;
        this.name = name;
        this.adress = adress;
    }

    public PlaceDescriptionAndPhoto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "PlaceDescriptionAndPhoto{" +
                "id=" + id +
                ", imageReference='" + imageReference + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
