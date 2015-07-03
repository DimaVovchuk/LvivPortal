package com.lab.epam.command.logination.fb;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Oleguk on 30.06.2015.
 */
public class FBResponseCommand implements Command {
    private String code = "";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserService userServ = null;
        User user = null;
        UserImageService userImageService = null;
        UserImage userImage = null;
        JSONObject json = null;
        code = request.getParameter("code");

        if (code == null || code.equals("")) {
            throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
        }
        FBConnection fbConnection = new FBConnection();
        String accessToken = fbConnection.getAccessToken(code);
        FBGraph fbGraph = new FBGraph(accessToken);
        String graph = fbGraph.getFBGraph();
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
        try {
            json = new JSONObject(graph);
            String photo = "http://graph.facebook.com/" + json.get("id") + "/picture?width=200&height=200";
            userServ = new UserService();
            if (userServ.getUserByVkId((String)json.get("id")).getVkId() != null) {
                user = userServ.getUserByVkId(json.getString("id"));
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
                session.setAttribute("vk_id", json.getString("id"));
                session.setAttribute("avatar", userImage.getReference());
                request.getRequestDispatcher("/views/pages/user-cabinet.jsp").forward(request, response);
            } else {
                json = new JSONObject(graph);
                String id = (String)json.get("id");
                String first_name = (String) json.get("first_name");
                String last_name = (String) json.get("last_name");
                String email = (String) json.get("email");

                session.setAttribute("vk_id", id);
                session.setAttribute("avatar", photo);

                request.getRequestDispatcher("/portal?command=signUpForm&first=" + first_name + "&last=" + last_name + "&email=" + email).include(request, response);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
