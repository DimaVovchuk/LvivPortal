package com.lab.epam.command.email;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
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
 * Created by Dima on 15-Jun-15.
 */
public class ConfirmChangePassCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");
        UserService userService = new UserService();
        User user = userService.getByPK(userID);
        String password = request.getParameter("password");
        String md5 = MD5Creator.getMD5(password+user.getLogin());
        user.setPassword(md5);
        try {
            userService.update(user);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        loger.info("Command ConfirmChangePassCommand");
        request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
    }
}
