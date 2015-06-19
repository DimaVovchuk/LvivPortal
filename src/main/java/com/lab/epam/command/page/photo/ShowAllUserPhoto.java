package com.lab.epam.command.page.photo;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.UserImage;
import com.lab.epam.service.UserImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vasyl on 17.06.2015.
 */
public class ShowAllUserPhoto implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userID = (Integer)session.getAttribute("usedID");
        UserImageService userImageService = new UserImageService();
        List<UserImage> userImageList = userImageService.getUserImageByUserId(userID);
        request.setAttribute("AllUserPhoto",userImageList);
        request.getRequestDispatcher("/views/pages/uploadTest.jsp").forward(request, response);
    }
}
