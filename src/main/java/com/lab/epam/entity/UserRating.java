package com.lab.epam.entity;

import com.lab.epam.dao.Identified;
import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.07.2015.
 */
@Table("user_rating")
public class UserRating implements Identified<Integer> {

@Column("id")
private Integer id = null;
@Column("user_id")
private Integer user_id;

    public UserRating(Integer user_id, Integer company_id, Integer rating) {
        this.user_id = user_id;
        this.company_id = company_id;
        this.rating = rating;
    }

    public UserRating() {

    }

    public UserRating(Integer id, Integer user_id, Integer company_id, Integer rating, Boolean deleted) {

        this.id = id;
        this.user_id = user_id;
        this.company_id = company_id;
        this.rating = rating;
        this.deleted = deleted;
    }

    public Integer getCompany_id() {

        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Column("company_id")
private Integer company_id;
@Column("rating")
private Integer rating;
@Column("deleted")
private Boolean deleted = false;
}
