package com.lab.epam.command.email;

import com.lab.epam.command.Command;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserService;
import com.lab.epam.smtp.SendEmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Dima on 14-Jun-15.
 */
public class SendChangePassMailCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserService userService = new UserService();
        User user = userService.geUserByEmail(email);
        HttpSession session = request.getSession();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        long timeMillis = System.currentTimeMillis();
        String s = new String(bundle.getString("reset.password").getBytes("ISO-8859-1"), "windows-1251") + " -> <a href = 'http://localhost:8080/portal?command=checkTime&user=" + email + "&param=" + timeMillis + "'> http://localhost:8080/portal?command=command=resetPass&user=" + email + "&param=" + timeMillis + "' <a>";
        if(user.getPassword() != null){
            SendEmail.sender("Change password",s,email);
        }
        loger.info("Command SendChangePassMailCommand");
        response.sendRedirect("portal?command=index");


    }
}
