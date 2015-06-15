package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 11.06.2015.
 */
@Table("place_description")
public class PlaceDescription implements Identified<Integer>{

        @Column("id")
        private Integer id = null;
        @Column("place_id")
        private Integer place_id;
        @Column("locale")
        private String locale;
        @Column("name")
        private String name;
        @Column("description")
        private String description;
        @Column("deleted")
        private Boolean deleted = false;
        @Column("phone")
        private String phone;
        @Column("price")
        private String price;

        public PlaceDescription(Builder builder) {
            this.place_id = builder.place_id;
            this.name = builder.name;
            this.locale = builder.locale;
            this.description = builder.description;
        }

        public PlaceDescription(){
        }

        @Override
        public String toString() {
            return "PlaceDescription{" +
                    "id=" + id +
                    ", place_id='" + place_id + '\'' +
                    ", locale='" + locale + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        protected void setId(Integer id) {
            this.id = id;
        }

        public Integer getPlace_id() { return place_id;}

        public void setPlace_id(Integer id) {
        this.place_id = place_id;
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

        public void setName(String name) { this.name = name;}

        public String getDescription() {
            return description;
        }

        public void setdDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
        return price;
    }

        public void setPrice(String price) {
        this.price = price;
    }

        public String getPhone() {
        return phone;
    }

        public void setPhone(String phone) {
        this.phone = phone;
    }

    public static class Builder {
        private Integer place_id;
        private String locale;
        private String name;
        private String description;

        private String phone;
        private String price;

        public Builder(Integer place_id, String locale, String name, String description) {
            this.place_id = place_id;
            this.name = name;
            this.locale = locale;
            this.description = description;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder price(String val) {
            price = val;
            return this;
        }

        public PlaceDescription build() {
            return new PlaceDescription(this);
        }
    }

}
