package com.lab.epam.command.email;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.md5.MD5Creator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dima on 13-Jun-15.
 */
public class ConfirmEmailCommand implements Command {
private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        String login = request.getParameter("user");
        MySqlUserDao mySqlUserDao = new MySqlUserDao();
        User userByLogin = mySqlUserDao.getUserByLogin(login);
        String md5Phone = MD5Creator.getMD5(userByLogin.getPhone());
        if(md5Phone.equals(param)){
            if(userByLogin.getStatus() == 3){
                response.sendRedirect("portal?command=index");
                return;
            }
            userByLogin.setStatus(2);
            loger.info("User " +login+ " is activated");
        }
        try {
            mySqlUserDao.update(userByLogin);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update user");
        }
        HttpSession session = request.getSession();
        session.setAttribute("login",login);
        session.setAttribute("role",userByLogin.getRoleID());
        loger.info("User " +login+ " signing in ");
        response.sendRedirect("portal?command=index");
    }
}
