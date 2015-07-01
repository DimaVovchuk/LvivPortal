package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 01.07.2015.
 */

    @Table("way_rating")
    public class WayRating implements Identified<Integer> {

        @Column("id")
        private Integer id = null;
        @Column("user_id")
        private Integer user_id;
        @Column("way_id")
        private Integer way_id;
        @Column("rating")
        private Integer rating;
        @Column("deleted")
        private Boolean deleted = false;

        public WayRating(Integer user_id, Integer way_id, Integer rating) {
            this.rating = rating;
            this.user_id = user_id;
            this.way_id = way_id;
        }

        public WayRating(){
        }

        @Override
        public String toString() {
            return "PlaceResponse{" +
                    "id=" + id +
                    ", rating='" + rating + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", way_id='" + way_id + '\'' +
                    ", deleted=" + deleted +
                    '}';
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

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public Integer getWay_id() {
            return way_id;
        }

        public void setWay_id(Integer way_id) {
            this.way_id = way_id;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }
    }



