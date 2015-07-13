package com.lab.epam.command.logination.vk;

import com.lab.epam.helper.ClassName;
import com.lab.epam.vk.TokenAccess;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UriCreator {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private static final String METHOD_URI = "https://api.vk.com/method/";
    public static final String USER_FIELDS = "first_name,last_name,about,contacts,photo_200";

    public String userInfoUri(String vkUserId, TokenAccess token) {
        loger.info("Get user info URI");
        return METHOD_URI + "users.get?" + 
                "uids=" + vkUserId + "&" + 
                "fields=" + USER_FIELDS + "&" +
                "access_token=" + token.getToken();
    }
}
