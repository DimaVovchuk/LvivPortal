package com.lab.epam.vk;

/**
 * Created by Oleguk on 29.06.2015.
 */
public class VkObject {
    private VkAppConfig vkAppConf = new VkAppConfig();
    private TokenAccess token = null;
    private Authorization auth = null;

    public VkObject() {
        super();
    }

    public VkObject(int appId) {
        vkAppConf.setApplicationID(appId);
    }

    public VkAppConfig vkConfig() {
        return vkAppConf;
    }

    public Authorization getAuth() {
        return auth;
    }

    public TokenAccess getAccessToken() {
        return token;
    }

    public void setAccessToken(TokenAccess accessToken) {
        this.token = accessToken;
    }

    public Authorization createAuthorization() {
        auth = new Authorization(vkAppConf);
        return auth;
    }

//    public Users users() {
//        return new Users(token);
//    }

    public Authorization auth() {
        return new Authorization(vkAppConf);
    }
}
