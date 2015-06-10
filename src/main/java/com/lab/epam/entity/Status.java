package com.lab.epam.entity;

import com.lab.epam.transformer.Column;
import com.lab.epam.transformer.Table;

/**
 * Created by Admin on 10.06.2015.
 */
@Table("status")
public class Status {

    @Column("id")
    private Integer id;
    @Column("status")
    private String status;
    @Column("deleted")
    private Boolean deleted;

    public Status(Integer id, String status, Boolean deleted){
        this.id = id;
        this.status = status;
        this.deleted = deleted;
    }

    public Status(){
        this.id = null;
        this.status = null;
        this.deleted = null;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status=" + status +
                ", deleted='" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;

    }

}
