package com.lab.epam.entity;

/**
 * Created by Admin on 10.06.2015.
 */
public class Category {

        private Integer id;
        private String category;
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
