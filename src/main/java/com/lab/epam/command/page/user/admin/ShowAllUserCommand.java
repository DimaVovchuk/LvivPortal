package com.lab.epam.command.page.user.admin;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.RoleService;
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
 * Created by Vasyl on 17.06.2015.
 */
public class ShowAllUserCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loger.info("ShowAllUserCommand command");
        List<User> userList = new ArrayList<>();
        Map<User, String> userRole = new HashMap<User, String>();
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        Integer servletUserId = null;
        Integer changeRoleID =null;
        Integer changeStatus = null;

        String requestType = request.getParameter("requestType");
        String servletUserIdString = request.getParameter("servletUserId");
        loger.info("requestType "  + requestType);
        if(servletUserIdString != null){
            servletUserId = Integer.valueOf(servletUserIdString);
            loger.info("User id is "  + servletUserId);
        }

        String changeRoleIDString = request.getParameter("changeRoleID");
        if(changeRoleIDString != null && requestType.equalsIgnoreCase("changeRole")){
            loger.info("changeRoleIDString " + changeRoleIDString);
            User userChangeRole = userService.getByPK(servletUserId);
            changeRoleID = Integer.valueOf(changeRoleIDString);
            loger.info("changeRoleID " + changeRoleID);

            userChangeRole.setRoleID(changeRoleID);
            try {
                userService.update(userChangeRole);
                session.setAttribute("role", changeRoleID);
                showAllUser(userRole,roleService,userService,request);
                loger.info("user role is succesful changed ");
            } catch (PersistException e) {
                loger.warn(e.getMessage());
            }
        }

        String changeStatusIDString = request.getParameter("changeStatusID");
        if(changeStatusIDString != null && requestType.equalsIgnoreCase("changeStatus")){
            loger.info("changeStatusIDString " + changeStatusIDString);
            changeStatus = Integer.valueOf(changeStatusIDString);
            loger.info("changeStatus " + changeStatus);
            User userChangeStatus = userService.getByPK(servletUserId);
            userChangeStatus.setStatus(changeStatus);
            try {
                userService.update(userChangeStatus);
                session.setAttribute("statusID", changeStatus);
                showAllUser(userRole, roleService, userService, request);
                loger.info("user status is successful changed ");
            } catch (PersistException e) {
                loger.warn(e.getMessage());
            }
        }

        if(requestType.equalsIgnoreCase("showAllUser")){
            showAllUser(userRole,roleService,userService,request);
            loger.info("Show all user.");
        }

        request.getRequestDispatcher("/views/pages/admin-users.jsp").forward(request, response);
    }

    private void showAllUser(Map<User, String> userRole,RoleService roleService, UserService userService,HttpServletRequest request){
        try {
            List<User>userList = userService.getAll();
            for (User anUserList : userList) {
                Integer roleID = userService.getRoleID(anUserList.getLogin());
                String roleName = roleService.getByPK(roleID).getRole();
                userRole.put(anUserList, roleName);
            }
            request.setAttribute("AllUsers",userRole);
        } catch (PersistException e) {
            loger.warn(e.getMessage());
        }
    }
}
