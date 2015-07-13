package com.lab.epam.command.logination;

import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleguk on 02.07.2015.
 */
public class SignUpForm implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command SignUpForm");
        String first_name = request.getParameter("first");
        String last_name = request.getParameter("last");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String vk_id = request.getParameter("vk_id");
        System.out.println("----------------------333");
        System.out.println(vk_id);
        System.out.println("----------------------333");
        //request.setAttribute("vk_id", vk_id);
        request.setAttribute("first", first_name);
        request.setAttribute("last", last_name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.getRequestDispatcher("/views/modals/login.jsp").forward(request, response);
    }
}
