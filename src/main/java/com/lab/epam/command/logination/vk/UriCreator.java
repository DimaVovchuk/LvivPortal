package com.lab.epam.command.logination.vk;

import com.lab.epam.vk.TokenAccess;
//import org.apache.commons.lang3.StringUtils;

public class UriCreator {

    private static final String METHOD_URI = "https://api.vk.com/method/";
    public static final String USER_FIELDS = "first_name,last_name,about,contacts,photo_200";

    public String authUri(String appId, String[] scopes, String responseUri) {
        return "http://oauth.vk.com/authorize?" + 
                "client_id=" + appId + "&" + 
                //"scope=" + StringUtils.join(scopes, ",") + "&" +
                "redirect_uri=" + responseUri + "&" + 
                "response_type=code";
    }

    public String accessTokenUri(String appId, String appKey, String code) {
        return "https://oauth.vk.com/access_token?client_id=" + appId + "&client_secret=" + appKey + "&code=" + code;
    }

    public String userInfoUri(String vkUserId, TokenAccess token) {
        return METHOD_URI + "users.get?" + 
                "uids=" + vkUserId + "&" + 
                "fields=" + USER_FIELDS + "&" +
                "access_token=" + token.getToken();
    }
}
