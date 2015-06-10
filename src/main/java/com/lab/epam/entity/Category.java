package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("category")
public class Category implements Identified<Integer> {

        @Column("id")
        private Integer id;
        @Column("category")
        private String category;
        @Column("deleted")
        private Boolean deleted;

        public Category(Integer id, String category, Boolean deleted){
            this.id = id;
            this.category = category;
            this.deleted = deleted;
        }

        public Category(){
            this.id = null;
            this.category = null;
            this.deleted = null;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", category=" + category +
                    ", deleted='" + deleted +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        protected void setId(Integer id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;

        }
}
