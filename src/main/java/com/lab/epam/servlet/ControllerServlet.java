package com.lab.epam.servlet;

import com.lab.epam.command.controller.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vasyl on 09.06.2015.
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControllerServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        CommandFactory.createCommand(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        CommandFactory.createCommand(request, response);
    }
}

