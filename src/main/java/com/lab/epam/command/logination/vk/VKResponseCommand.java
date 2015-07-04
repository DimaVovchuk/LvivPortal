package com.lab.epam.command.logination.vk;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserImageService;
import com.lab.epam.vk.TokenAccess;
import com.lab.epam.vk.VkObject;
import com.lab.epam.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.json.JSONObject;


/**
 * Created by Oleguk on 29.06.2015.
 */
public class VKResponseCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private final HttpClient httpclient = new DefaultHttpClient();
    UserImageService userImageService = null;
    UserImage userImage = null;
    UserService userServ = null;
    User user = null;
    JSONObject json = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command VKResponseCommand");
        userServ = new UserService();
        userImageService = new UserImageService();
        userImage = new UserImage();
        user = new User();
        HttpSession session = request.getSession();

        VkObject vkObj = (VkObject)session.getAttribute("vObj");
        TokenAccess token = null;
        try {
            token = vkObj.getAuth().signIn(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String uri = new UriCreator().userInfoUri(token.getVkUserId(), token);
        String url = executeGet(uri);
        url = url.substring(13, url.length()-2);
        try {
            json = new JSONObject(url);
            vkObj.setAccessToken(token);
            userServ = new UserService();

            String photo = (String) json.get("photo_200");

            if (userServ.getUserByVkId(token.getVkUserId()).getVkId() != null) {
                user = userServ.getUserByVkId(token.getVkUserId());

                session.setAttribute("login", user.getLogin());
                session.setAttribute("usedID", user.getId());
                session.setAttribute("vk_id", token.getVkUserId());
                session.setAttribute("role", user.getRoleID());
                session.setAttribute("avatar_id", user.getAvatar());

                if (user.getAvatar() != null) {
                    userImage = userImageService.getByPK(user.getAvatar());
                } else {
                    userImage = new UserImage(user.getId(), photo);
                }
                session.setAttribute("ava", userImage.getReference());

                loger.info("User sign in with vk");
                request.getRequestDispatcher("/views/pages/user-cabinet.jsp").forward(request, response);

            } else {
                json = new JSONObject(url);
                String vk_id = token.getVkUserId();
                String first_name = (String) json.get("first_name");
                String last_name = (String) json.get("last_name");
                String email = token.getEmail();
                String phone = (String) json.get("home_phone");

                session.setAttribute("ava", photo);
                session.setAttribute("vk_id", vk_id);

                loger.info("User sign up with vk");
                request.getRequestDispatcher("/portal?command=signUpForm&first=" + first_name + "&last=" + last_name + "&phone=" + phone + "&email=" + email).include(request, response);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String executeGet(String uri) {
        return executeRequest(new HttpGet(uri));
    }

    private String executeRequest(HttpUriRequest request) {
        try {
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return IOUtils.toString(entity.getContent(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
