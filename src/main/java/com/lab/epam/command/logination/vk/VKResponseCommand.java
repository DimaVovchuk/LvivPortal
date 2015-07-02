package com.lab.epam.command.logination.vk;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
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
    private final HttpClient httpclient = new DefaultHttpClient();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userServ = null;
        UserImageService userImageService = null;
        User user = null;
        UserImage userImage = null;
        JSONObject json = null;
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
        System.out.println(url);
        url = url.substring(13, url.length()-2);
        try {
            json = new JSONObject(url);
            vkObj.setAccessToken(token);
            userServ = new UserService();

            String photo = (String) json.get("photo_200");

            if (userServ.getUserByVkId(token.getVkUserId()).getVkId() != null) {
                user = userServ.getUserByVkId(token.getVkUserId());

                session.setAttribute("first", user.getName());
                session.setAttribute("last", user.getSurname());
                session.setAttribute("companyname", user.getCompanyName());
                session.setAttribute("login", user.getLogin());
                session.setAttribute("email", user.getMail());
                session.setAttribute("password", user.getPassword());
                session.setAttribute("phone", user.getPhone());
                session.setAttribute("role", user.getRoleID());
                session.setAttribute("about", user.getAbout());

                if (user.getAvatar() != null) {
                    userImage = userImageService.getByPK(user.getAvatar());
                } else {
                    userImage = new UserImage(user.getId(), photo);
                }
                session.setAttribute("userForEdit", user);
                session.setAttribute("vk_id", token.getVkUserId());
                session.setAttribute("avatar", userImage.getReference());
                request.getRequestDispatcher("/views/pages/user-cabinet.jsp").forward(request, response);
            } else {
                user = new User();
                json = new JSONObject(url);
                int uid = json.getInt("uid");
                String first_name = (String) json.get("first_name");
                String last_name = (String) json.get("last_name");
                String home_phone = (String) json.get("home_phone");
                String about = (String) json.get("about");

                user.setMail(token.getEmail());
                user.setVkId(String.valueOf(uid));
                user.setName(first_name);
                user.setSurname(last_name);
                user.setPhone(home_phone);
                user.setAbout(about);

                session.setAttribute("vk_id", token.getVkUserId());
                session.setAttribute("avatar", photo);
                session.setAttribute("userForEdit", user);
                request.getRequestDispatcher("/views/pages/editProfile.jsp").forward(request, response);
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
