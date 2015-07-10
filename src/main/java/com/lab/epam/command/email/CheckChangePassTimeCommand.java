package com.lab.epam.command.email;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckChangePassTimeCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("user");
        UserService userService = new UserService();
        User user = userService.geUserByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("userID",user.getId());
        long time = Long.parseLong(request.getParameter("param"));
        long timeMillis = System.currentTimeMillis();
        if((timeMillis-time)<3600000){
            request.setAttribute("modal", "resetConfirm");
        } else {
            request.setAttribute("modal", "resetSendEmail");
        }
        loger.info("Command CheckChangePassTimeCommand.");
        request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
    }
}
