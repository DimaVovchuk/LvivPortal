package com.lab.epam.command.page.user;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.ImageResponseService;
import com.lab.epam.service.PlaceResponseService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 12.07.2015.
 */
public class DeletImageResponseCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command DeletImageResponseCommand");

        String response_idString = request.getParameter("response_id");
        Integer response_id = 0;
        Integer isDeleted = 0;

        ImageResponseService imageResponseService = new ImageResponseService();
        UserService userService = new UserService();

        if (response_idString != null) {
            response_id = Integer.parseInt(response_idString);
        }

        if (response_id != 0) {
            imageResponseService.deleteResponseByUserIdImageId(response_id);
            isDeleted = 1;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(isDeleted));

    }
}
