package com.lab.epam.command.email;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Decoder;
import com.lab.epam.helper.ClassName;
import com.lab.epam.smtp.SendEmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Oleguk on 05.07.2015.
 */
public class SendContactUsMailCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    String message = null;
    String name = null;
    String email = null;
    String theme = null;
    HttpSession session = null;
    String[] paramNames = null;
    Enumeration<String> parNamesEnum = null;
    int counter = 0;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            session = request.getSession();
            parNamesEnum = request.getParameterNames();
            paramNames = new String[5];
            while (parNamesEnum.hasMoreElements()) {
                paramNames[counter] = Decoder.decodeStringUtf8(parNamesEnum.nextElement());
                counter++;
            }
            counter = 0;
            name = Decoder.decodeStringUtf8(request.getParameter(Decoder.decodeStringUtf8(paramNames[1])));
            email = Decoder.decodeStringUtf8(request.getParameter(Decoder.decodeStringUtf8(paramNames[2])));
            theme = Decoder.decodeStringUtf8(request.getParameter("theme"));
            message = Decoder.decodeStringUtf8(request.getParameter(Decoder.decodeStringUtf8(paramNames[4])));

            if (name != "" && email != "" && theme != "" && message != "") {
                if ("mail".equals(paramNames[2])) {
                    message = "Name: " + name + "  E-mail: " + email + "  Message: " + message;
                    SendEmail.sender(theme, message, "mail.for.blablabla@gmail.com");
                } else {
                    message = "Ім'я: " + name + "  Електронна пошта: " + email + "  Повідомлення: " + message;
                    SendEmail.sender(theme, message, "mail.for.blablabla@gmail.com");
                }
                loger.info("Command SendContactUsMailCommand");
                //response.sendRedirect("portal?command=about");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(1));
            } else {
                loger.info("Not all fields was filled");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(0));
                //response.sendRedirect("portal?command=about");
            }
        }catch(Exception e){
            loger.info("Command SendContactUsMailCommand failed");
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(2));
            //response.sendRedirect("portal?command=about");
        }
    }
}
