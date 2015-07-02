package com.lab.epam.vk;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oleguk on 29.06.2015.
 */
public class TokenAccess {

    private String token;
    private int vkUserId;
    private Date expirationMoment;
    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getVkUserId() {
        return vkUserId;
    }

    public void setVkUserId(int id) {
        this.vkUserId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpirationMoment() {
        return expirationMoment;
    }

    public void setExpirationMoment(long time) {
        expirationMoment = new Date(System.currentTimeMillis()
                + TimeUnit.SECONDS.toMillis(time));
    }

    public String toString() {
        String tokenAcc = "AccessToken [accessToken=" + token + ", expirationMoment=" + expirationMoment + ", userId="
                + vkUserId + "]";
        return tokenAcc;
    }
}
