package com.lab.epam.command.email;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.dao.imp.MySqlUserDao;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.md5.MD5Creator;
import com.lab.epam.service.UserImageService;
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
        HttpSession session = request.getSession();
        if(md5Phone.equals(param)){
            if(userByLogin.getStatus() == 3){
                response.sendRedirect("portal?command=index");
                return;
            }
            userByLogin.setStatus(1);
            loger.info("User " +login+ " is activated");
        }
        try {
            mySqlUserDao.update(userByLogin);
        } catch (PersistException e) {
            e.printStackTrace();
            loger.warn("Cant update user");
        }

        String avatarReference = null;
        Integer userAvatarID = userByLogin.getAvatar();

        if (userAvatarID != null && userAvatarID!=0) {
            UserImageService userImageService = new UserImageService();
            UserImage userImagee = userImageService.getByPK(userAvatarID);
            avatarReference = userImagee.getReference();
            loger.info("Get avatar reference");
        }

        if(avatarReference !=null) {
            session.setAttribute("avatarReference", avatarReference);
        } else{
            session.setAttribute("avatarReference", "user.png");
        }

        session.setAttribute("login",login);
        session.setAttribute("userID", userByLogin.getId());
        session.setAttribute("role",userByLogin.getRoleID());
        session.setAttribute("avatarReference",avatarReference);
        loger.info("User " +login+ " signing in ");
        response.sendRedirect("portal?command=index");
    }
}
