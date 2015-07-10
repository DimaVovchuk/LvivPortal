package com.lab.epam.command.logination.vk;

import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import com.lab.epam.vk.VkObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Oleguk on 29.06.2015.
 */
public class VkAuthorizationCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private VkObject vObj = null;

    public VkAuthorizationCommand() {
        vObj = new VkObject(4974533);
        vObj.vkConfig().setAppSecretKey("1vgWKZCPSQmY0ymy3BFr");
        vObj.vkConfig().setPermissions("email");
        vObj.createAuthorization();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command VkAuthorizationCommand");
        HttpSession session = request.getSession();
        session.setAttribute("vObj", vObj);
        vObj.getAuth().autorize(response);
    }
}