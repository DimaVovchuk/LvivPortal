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

        public PlaceDescription(Integer place_id, String locale, String name, String descriptione) {
            this.place_id = place_id;
            this.name = name;
            this.locale = locale;
            this.description = description;
        }

        public PlaceDescription(){
        }

        @Override
        public String toString() {
            return "Place{" +
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

        public Integer getPlaceId() { return place_id;}

        public void setPlaceId(Integer id) {
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

        public void setdDscription(String description) {
            this.description = description;
        }
}
