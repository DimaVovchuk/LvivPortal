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
        loger.info("ShowAllUserCommand command");
        List<User> userList = new ArrayList<>();
        Map<User, String> userRole = new HashMap<User, String>();
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        try {
            userList = userService.getAll();
            for (int i = 0; i < userList.size(); i++) {
                Integer roleID= userService.getRoleID(userList.get(i).getLogin());
                loger.info("roleID " + roleID);
                String roleName = roleService.getByPK(roleID).getRole();
                loger.info("roleName " + roleName);
                userRole.put(userList.get(i),roleName);
            }
            request.setAttribute("AllUsers",userRole);
        } catch (PersistException e) {
            loger.warn(e.getMessage());
        }

        request.getRequestDispatcher("/views/pages/admin_cabinet.jsp").forward(request, response);
    }
}
