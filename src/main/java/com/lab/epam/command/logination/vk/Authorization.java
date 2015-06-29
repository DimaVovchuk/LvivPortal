package com.lab.epam.command.logination.vk;

import com.lab.epam.entity.VkAppConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleguk on 29.06.2015.
 */
public class Authorization {

    private VkAppConfig vkConfig;


    public Authorization(VkAppConfig vkConfig) {
        this.vkConfig = vkConfig;
    }

    public void authorize(HttpServletResponse response) {
        String authURI = "https://oauth.vk.com/authorize?" +
                "client_id=" + vkConfig.getApplicationID() + "&scope=email" +
                "&redirect_uri=" + vkConfig.getSiteAddress() +
                "&v=5.34" + "&response_type=code";
        try {
            response.sendRedirect(authURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
