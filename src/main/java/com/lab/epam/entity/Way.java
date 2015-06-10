package com.lab.epam.entity;

import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("way")
public class Way {
    @Column("id")
    private Integer id;
    @Column("rating")
    private Integer rating;
    @Column("name")
    private String name;
    @Column("visible")
    private String visible;
    @Column("deleted")
    private Boolean deleted;

    public Way(Integer id, Integer rating, String name, String visible, Boolean deleted){
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.visible = visible;
        this.deleted = deleted;
    }

    public Way(){
        this.id = null;
        this.rating = null;
        this.name = null;
        this.visible = null;
        this.deleted = null;
    }

    @Override
    public String toString() {
        return "Way{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
