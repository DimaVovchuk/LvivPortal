package com.lab.epam.command.page.user;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vasyl on 27.06.2015.
 */
public class CommercialCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
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
            Integer avatarID = allCommercialUser.get(index).getAvatar();
            if(avatarID !=null) {
                userImage = userImageService.getByPK(avatarID);
            } else{
                userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
            }
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
                        Integer avatarID = userGuideList.get(index).getAvatar();
                        if(avatarID !=null) {
                            userImage= userImageService.getByPK(avatarID);
                        } else{
                            userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
                        }
                        guideAvatarMap.put(userImage, userGuideList.get(index));
                    }
                    loger.info("guideAvatarMap "+ guideAvatarMap);
                    session.setAttribute("allUserAvatarMap",guideAvatarMap);
                    break;
                case "agency":
                    for(int index = 0; index < userConpanyList.size();index++) {
                        Integer avatarID = userConpanyList.get(index).getAvatar();
                        if(avatarID !=null) {
                            userImage= userImageService.getByPK(avatarID);
                        } else{
                            userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
                        }
                        agencyAvatarMap.put(userImage, userConpanyList.get(index));
                    }
                    loger.info("agencyAvatarMap "+ agencyAvatarMap);
                    session.setAttribute("allUserAvatarMap",agencyAvatarMap);
                    break;
                default:
                    session.setAttribute("allUserAvatarMap",allUserAvatarMap);
                    break;
            }
        } else {
            session.setAttribute("allUserAvatarMap",allUserAvatarMap);
            loger.info("allUserAvatarMap "+ allUserAvatarMap);
        }

        request.getRequestDispatcher("/views/pages/commercial_page.jsp").forward(request, response);
    }
}
