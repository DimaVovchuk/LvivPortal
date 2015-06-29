package com.lab.epam.entity;

/**
 * Created by Oleguk on 29.06.2015.
 */
public class VkAppConfig {
    private int applicationID;
    private String appSecretKey;
    private String siteAddress;
    private String permissions;

    public VkAppConfig(){

    }

    public VkAppConfig(int applicationID, String appSecretKey, String siteAddress, String permissions) {
        this.appSecretKey = appSecretKey;
        this.applicationID = applicationID;
        this.siteAddress = siteAddress;
        this.permissions = permissions;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public String getAppSecretKey() {
        return appSecretKey;
    }

    public void setAppSecretKey(String appSecretKey) {
        this.appSecretKey = appSecretKey;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "VkAppConfig{" +
                "applicationID=" + applicationID +
                ", appSecretKey='" + appSecretKey + '\'' +
                ", siteAddress='" + siteAddress + '\'' +
                '}';
    }
}