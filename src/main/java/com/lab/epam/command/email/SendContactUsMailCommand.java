package com.lab.epam.command.email;

import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import com.lab.epam.smtp.SendEmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Oleguk on 05.07.2015.
 */
public class SendContactUsMailCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    String message = null;
    String name = null;
    String email = null;
    HttpSession session = null;
    //String messag = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            session = request.getSession();
            name = request.getParameter("name");
            email = request.getParameter("email");
            message = request.getParameter("message");

            if (name != "" && email != "" && message != "") {
                message = "Name: " + name + "\nE-mail: " + email + "\nMessage:\n" + message;
                SendEmail.sender("ContactUs message", message, "mail.for.blablabla@gmail.com");

                //messag = "sent";
                loger.info("Command SendContactUsMailCommand");
                response.sendRedirect("portal?command=about");
            } else {
                //messag = "emp";
                loger.info("Not all fields was filled");
                response.sendRedirect("portal?command=about");
            }
        }catch(Exception e){
            //messag = "failed";
            loger.info("Command SendContactUsMailCommand failed");
            e.printStackTrace();
            response.sendRedirect("portal?command=about");
        }
    }
}
