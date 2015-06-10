package com.lab.epam.entity;

/**
 * Created by Admin on 10.06.2015.
 */
public class Role {

    private Integer id;
    private String role;
    private Boolean deleted;

    public Role(Integer id, String role, Boolean deleted){
        this.id = id;
        this.role = role;
        this.deleted = deleted;
    }

    public Role(){
        this.id = null;
        this.role = null;
        this.deleted = null;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + role +
                ", deleted='" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


}
