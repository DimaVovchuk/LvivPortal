package com.lab.epam.command.page.user.admin;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vasyl on 06.07.2015.
 */
public class ShowAdminCabinetCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");
        loger.info("userID " + userID);
        loger.info("Start ShowAdminCabinetCommand");

        if (userID != null) {
            UserService userService = new UserService();
            User user = userService.getByPK(userID);
            Integer avatar_id = user.getAvatar();
            if (avatar_id != null) {
                UserImageService adminAvatar = new UserImageService();
                UserImage avatarUserImage = adminAvatar.getByPK(avatar_id);
                request.setAttribute("adminAvatar", avatarUserImage.getReference());
            }
            request.setAttribute("adminData", user);
            loger.info("End ShowAdminCabinetCommand");
            request.getRequestDispatcher("/views/pages/admin-page.jsp").forward(request, response);
        }
    }
}
