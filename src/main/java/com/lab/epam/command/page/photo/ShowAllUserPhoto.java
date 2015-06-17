package com.lab.epam.command.page.photo;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.UserImage;
import com.lab.epam.service.UserImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vasyl on 17.06.2015.
 */
public class ShowAllUserPhoto implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserImageService userImageService = new UserImageService();
        List<UserImage> userImageList = userImageService.getAll();
        request.setAttribute("AllUserPhoto",userImageList);
        request.getRequestDispatcher("/views/pages/all_user_photo.jsp").forward(request, response);
    }
}
