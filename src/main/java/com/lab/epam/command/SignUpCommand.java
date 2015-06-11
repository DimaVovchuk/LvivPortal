package com.lab.epam.command;

import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vasyl on 11.06.2015.
 */
public class SignUpCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command SignUpCommand.");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setRating(0);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setMail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setStatus("notective");
        user.setRoleID(20);

        MySqlUserDao mu = new MySqlUserDao();
        try {
            mu.create(user);
            loger.info("New user was added");
            request.getRequestDispatcher("/views/pages/dashboard.jsp").forward(request, response);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.info("Adding new user was failed");
            loger.error(e.getMessage());
        }
    }
}
