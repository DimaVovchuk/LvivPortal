package com.lab.epam.command.page.user;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.ImageResponse;
import com.lab.epam.entity.ImageResponseAvatar;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.ImageResponseService;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public class CommercialImageCommentCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        ImageResponseService imageResponseService = new ImageResponseService();
        List<ImageResponse> imageResponses = new ArrayList<>();
        List<ImageResponseAvatar> imageResponsesAvatar = new ArrayList<>();
        UserService userService = new UserService();
        UserImageService userImageService = new UserImageService();

        String image_idString = request.getParameter("image_id");
        Integer image_id = 0;
        if (image_idString != null){
            image_id = Integer.parseInt(image_idString);
        }
        if (image_id != null){
            imageResponses = imageResponseService.getImageResponseByImage(image_id);
        }
        if (imageResponses != null && !imageResponses.isEmpty()) {
            for (ImageResponse imRes : imageResponses) {
                ImageResponseAvatar item = new ImageResponseAvatar();
                item.setId(imRes.getId());
                item.setDescription(imRes.getDescription());
                if (imRes.getUser_id() != null && imRes.getUser_id() > 0) {
                    User user = userService.getByPK(imRes.getUser_id());
                    UserImage userImage = null;
                    if (user.getAvatar() != null){
                        userImage = userImageService.getByPK(user.getAvatar());
                    }
                    if (userImage != null){
                        item.setAvaterReference(userImage.getReference());
                    }
                }
                if (item.getAvaterReference() == null){
                    item.setAvaterReference("user.png");
                }
                item.setId(imRes.getId());
                imageResponsesAvatar.add(item);
            }
        }


        loger.info("CommercialImageCommentCommand start.");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(imageResponsesAvatar));

    }
}

