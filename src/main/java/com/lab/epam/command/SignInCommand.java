package com.lab.epam.command;

import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vasyl on 12.06.2015.
 */
public class SignInCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService serviceUser = new UserService();
        User user = serviceUser.geUserByLogin(login);
        HttpSession session = request.getSession();
        if(user != null && user.getPassword() == MD5Creator.getMD5(password + login)){
            session.setAttribute("login",login);
            session.setAttribute("role",user.getRoleID());
            loger.info("User " +login+ " signing in ");
        }else{
            session.setAttribute("loginError",1);
            loger.info("login or password is incorrect");
        }
        request.getRequestDispatcher("/views/pages/dashboard.jsp");
    }
}
