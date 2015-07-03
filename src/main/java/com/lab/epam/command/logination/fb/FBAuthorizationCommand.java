package com.lab.epam.command.logination.fb;

import com.lab.epam.command.controller.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleguk on 02.07.2015.
 */
public class FBAuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FBConnection fbConnection = new FBConnection();
        fbConnection.getFBAuthUrl();
        response.sendRedirect(fbConnection.getFBAuthUrl());
    }
}
