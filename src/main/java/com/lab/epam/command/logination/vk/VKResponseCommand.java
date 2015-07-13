package com.lab.epam.command.logination.vk;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import com.lab.epam.vk.TokenAccess;
import com.lab.epam.vk.VkObject;
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
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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

        VkObject vkObj = (VkObject) session.getAttribute("vObj");
        TokenAccess token = null;
        try {
            token = vkObj.getAuth().signIn(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (token != null) {
            String uri = new UriCreator().userInfoUri(token.getVkUserId(), token);
            String url = executeGet(uri);
            url = url.substring(13, url.length() - 2);
            try {
                System.out.println(uri);
                json = new JSONObject(url);
                vkObj.setAccessToken(token);
                userServ = new UserService();

                String photo = (String) json.get("photo_200");

                if (userServ.getUserByVkId(token.getVkUserId()).getVkId() != null) {
                    user = userServ.getUserByVkId(token.getVkUserId());
                    session.setAttribute("login", user.getLogin());
                    session.setAttribute("userID", user.getId());
                    session.setAttribute("role", user.getRoleID());
                    if (user.getAvatar() != null) {
                        userImage = userImageService.getByPK(user.getAvatar());
                    } else {
                        session.setAttribute("vk_ava",1);
                        userImage = new UserImage(user.getId(), photo);
                    }
                    session.setAttribute("avatarReference", userImage.getReference());
                    loger.info("User sign in with vk");
                    request.getRequestDispatcher("/portal?command=index").forward(request, response);
                } else {
                    json = new JSONObject(url);
                    String vk_id = token.getVkUserId();
                    String first_name = (String) json.get("first_name");
                    String last_name = (String) json.get("last_name");
                    String email = token.getEmail();
                    User userByEmail = userServ.geUserByEmail(email);
                    System.out.println(userByEmail);
                    if (userByEmail.getLogin() != null && userByEmail.getVkId() == null) {
                        userByEmail.setVkId(vk_id);
                        try {
                            userServ.update(userByEmail);
                        } catch (PersistException e) {
                            e.printStackTrace();
                        }
                        session.setAttribute("login", userByEmail.getLogin());
                        session.setAttribute("userID", userByEmail.getId());
                        session.setAttribute("role", userByEmail.getRoleID());
                        if (userByEmail.getAvatar() != null) {
                            userImage = userImageService.getByPK(userByEmail.getAvatar());
                        }
                        if (userImage != null) {
                            session.setAttribute("avatarReference", userImage.getReference());
                        } else {
                            session.setAttribute("avatarReference", "user.png");
                        }
                        request.getRequestDispatcher("/portal?command=index").forward(request, response);
                    }
                    String phone = (String) json.get("home_phone");
                    loger.info("User sign up with vk");
                    request.setAttribute("vkId", vk_id);
                    request.setAttribute("first", first_name);
                    request.setAttribute("last", last_name);
                    request.setAttribute("email", email);
                    request.setAttribute("phone", phone);
                    request.setAttribute("modal", "signUpForm");
                    request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("modal", "signUpForm");
            request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
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
