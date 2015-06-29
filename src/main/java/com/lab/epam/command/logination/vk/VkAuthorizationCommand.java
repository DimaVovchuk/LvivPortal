package com.lab.epam.command.logination.vk;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.VkAppConfig;
import com.lab.epam.helper.ClassName;
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
    private VkAppConfig vkConfig = new VkAppConfig();
    private Authorization auth = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        vkConfig = new VkAppConfig(4974533, "1vgWKZCPSQmY0ymy3BFr", "http://localhost:8080/", "email");
        auth = new Authorization(vkConfig);
        HttpSession session = request.getSession();
        session.setAttribute("siteVk", this);
        auth.authorize(response);
    }
}
