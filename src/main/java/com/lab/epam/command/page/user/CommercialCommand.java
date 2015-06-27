package com.lab.epam.command.page.user;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserImage;
import com.lab.epam.service.UserImageService;
import com.lab.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vasyl on 27.06.2015.
 */
public class CommercialCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> allCommercialUser = new ArrayList<>();
        Map<UserImage,User> allUserAvatarMap = new HashMap<>();
        Map<UserImage,User> guideAvatarMap = new HashMap<>();
        Map<UserImage,User> agencyAvatarMap = new HashMap<>();
        HttpSession session = request.getSession();

        List<User> userGuideList = userService.getUserByRole(3);
        List<User> userConpanyList = userService.getUserByRole(4);
        allCommercialUser.addAll(userGuideList);
        allCommercialUser.addAll(userConpanyList);
        UserImage userImage =null;

        UserImageService userImageService = new UserImageService();
        for(int index = 0; index < allCommercialUser.size();index++) {
            userImage= userImageService.getByPK(allCommercialUser.get(index).getAvatar());
            allUserAvatarMap.put(userImage, allCommercialUser.get(index));
        }


        String role = request.getParameter("role");
        System.out.println("role " + role);
        if (role == null) {
            role = "";
        }
        if (role != null) {
            switch (role) {
                case "guide":
                    for(int index = 0; index < userGuideList.size();index++) {
                        userImage= userImageService.getByPK(userGuideList.get(index).getAvatar());
                        guideAvatarMap.put(userImage, userGuideList.get(index));
                    }
                    session.setAttribute("allUserAvatarMap",guideAvatarMap);
                    break;
                case "agency":
                    for(int index = 0; index < userConpanyList.size();index++) {
                        userImage= userImageService.getByPK(userConpanyList.get(index).getAvatar());
                        agencyAvatarMap.put(userImage, userConpanyList.get(index));
                    }
                    session.setAttribute("allUserAvatarMap",agencyAvatarMap);
                    break;
                default:
                    session.setAttribute("allUserAvatarMap",allUserAvatarMap);
                    break;
            }
        } else {
            session.setAttribute("allUserAvatarMap",allUserAvatarMap);
        }

        request.getRequestDispatcher("/views/pages/commercial_page.jsp").forward(request, response);
    }
}
