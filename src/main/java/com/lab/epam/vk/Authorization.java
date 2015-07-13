package com.lab.epam.vk;

import com.lab.epam.helper.ClassName;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleguk on 29.06.2015.
 */
public class Authorization {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private VkAppConfig vkAppConf;

    public Authorization() {

    }

    public Authorization(VkAppConfig vkAppConf) {
        this.vkAppConf = vkAppConf;
    }

    public void autorize(HttpServletResponse response) {
        String authURI = "https://oauth.vk.com/authorize?" +
                 "client_id=" + vkAppConf.getApplicationID() +
                 "&scope=" + vkAppConf.getPermissions() +
                 "&redirect_uri=" + "http://localhost:8080/portal?command=authorRespVK" +
                 "&v=3.4" + "&response_type=code";
        try {
            loger.info("autorize method");
            response.sendRedirect(authURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TokenAccess signIn(HttpServletRequest request) throws JSONException {
        String authURI = "https://oauth.vk.com/access_token?" +
                "client_id=" + vkAppConf.getApplicationID() +
                "&client_secret=" + vkAppConf.getAppSecretKey() +
                "&code=" + request.getParameter("code") +
                "&redirect_uri=" + "http://localhost:8080/portal?command=authorRespVK";
        String con = getResponseContext(authURI);
        loger.info("signIn method");
        return parserAccessToken(con);
    }

    private String getResponseContext(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpEntity entity = client.execute(httpGet).getEntity();
            loger.info("getResponseContext method");
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private TokenAccess parserAccessToken(String context) throws JSONException {
        TokenAccess token = new TokenAccess();
        JSONObject json = new JSONObject(context);
        if (json.has("access_token")) {
            token.setToken(json.getString("access_token"));
            token.setVkUserId(json.getString("user_id"));
            token.setExpirationMoment(json.getLong("expires_in"));
            if (!json.isNull("email"))
                token.setEmail(json.getString("email"));
        } else {
            return null;
        }
        loger.info("parserAccessToken method");
        return token;
    }
}
