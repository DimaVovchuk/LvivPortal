package com.lab.epam.command.page.user;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.ImageResponse;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.ImageResponseService;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 08.07.2015.
 */
public class AddImageResponseCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        ImageResponseService imageResponseService = new ImageResponseService();
        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();
        Integer isCreated = 0;

        String image_idString = request.getParameter("image_id");
        String description = request.getParameter("gallery-comment");
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        Integer user_id = 0;
        if (login != null){
            User user = userService.getUserByLogin(login);
            if (user != null){
                user_id = user.getId();
            }
        }

        Integer image_id = 0;
        if (image_idString != null){
            image_id = Integer.parseInt(image_idString);
        }


        if (image_id != 0 && description != null && !description.equals("") && user_id != 0){
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setImage_id(image_id);
            imageResponse.setDescription(description);
            imageResponse.setUser_id(user_id);
            imageResponseService.create(imageResponse);
            isCreated = 1;
        }

        loger.info("Command AddImageResponseCommand");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(isCreated));

    }
}
