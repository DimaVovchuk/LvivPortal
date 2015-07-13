package com.lab.epam.command.page.user;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.CompanyGuideImage;
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
import java.util.List;

/**
 * Created by Vasyl on 03.07.2015.
 */
public class CommercialJSONCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("CommercialJSONCommand start.");

        UserService userService = new UserService();
        CompanyGuideImage companyGuideImage = new CompanyGuideImage();
        List<User> allCommercialUser = new ArrayList<>();
        List<CompanyGuideImage> allUserAvatarList = new ArrayList<>();
        List<CompanyGuideImage> guideAvatarList = new ArrayList<>();
        List<CompanyGuideImage> agencyAvatarList = new ArrayList<>();
        HttpSession session = request.getSession();

        List<User> userGuideList = userService.getUserByRole(3);
        List<User> userConpanyList = userService.getUserByRole(4);
        allCommercialUser.addAll(userGuideList);
        allCommercialUser.addAll(userConpanyList);
        UserImage userImage =null;
        UserImageService userImageService = new UserImageService();

        String userRole = request.getParameter("comertsRole");

        if (userRole != null) {
            switch (userRole) {
                case "guide":
                    for(int index = 0; index < userGuideList.size();index++) {
                        Integer avatarID = userGuideList.get(index).getAvatar();
                        if(avatarID !=null) {
                            userImage= userImageService.getByPK(avatarID);
                        } else{
                            userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
                        }
                        guideAvatarList.add(getCompanyGuideImage(userGuideList.get(index), userImage));
                    }
                    loger.info("guideAvatarMap "+ guideAvatarList);
                    allUserAvatarList.addAll(guideAvatarList);
                    break;
                case "agency":
                    for(int index = 0; index < userConpanyList.size();index++) {
                        Integer avatarID = userConpanyList.get(index).getAvatar();
                        if(avatarID !=null) {
                            userImage= userImageService.getByPK(avatarID);
                        } else{
                            userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
                        }
                        agencyAvatarList.add(getCompanyGuideImage(userConpanyList.get(index), userImage));
                    }
                    loger.info("agencyAvatarMap "+ agencyAvatarList);
                    allUserAvatarList.addAll(agencyAvatarList);
                    break;
                default:
                    for(int index = 0; index < allCommercialUser.size();index++) {
                        Integer avatarID = allCommercialUser.get(index).getAvatar();
                        if(avatarID !=null) {
                            userImage = userImageService.getByPK(avatarID);
                        } else {
                            userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
                        }
                        allUserAvatarList.add(getCompanyGuideImage(allCommercialUser.get(index), userImage));
                    }
                    break;
            }
        } else {
            for(int index = 0; index < allCommercialUser.size();index++) {
                Integer avatarID = allCommercialUser.get(index).getAvatar();
                if(avatarID !=null) {
                    userImage = userImageService.getByPK(avatarID);
                } else {
                    userImage = new UserImage(allCommercialUser.get(index).getId(),"user.png");
                }
                allUserAvatarList.add(getCompanyGuideImage(allCommercialUser.get(index), userImage));
            }
            loger.info("allUserAvatarMap "+ allUserAvatarList);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(allUserAvatarList));
    }

    private CompanyGuideImage getCompanyGuideImage(User user, UserImage userImage ){
        CompanyGuideImage guifeCompanyObject = new CompanyGuideImage();
        guifeCompanyObject.setId(user.getId());
        guifeCompanyObject.setRating(user.getRating());
        guifeCompanyObject.setName(user.getName());
        guifeCompanyObject.setSurname(user.getSurname());
        guifeCompanyObject.setLogin(user.getLogin());
        guifeCompanyObject.setMail(user.getMail());
        guifeCompanyObject.setPassword(user.getPassword());
        guifeCompanyObject.setPhone(user.getPhone());
        guifeCompanyObject.setStatus(user.getStatus());
        guifeCompanyObject.setRole_id(user.getRole_id());
        guifeCompanyObject.setAbout(user.getAbout());
        guifeCompanyObject.setAvatar(user.getAvatar());
        guifeCompanyObject.setDeleted(user.getDeleted());
        guifeCompanyObject.setCompanyName(user.getCompanyName());
        guifeCompanyObject.setVk_id(user.getVkId());
        guifeCompanyObject.setReference(userImage.getReference());
        return guifeCompanyObject;
    }
}
