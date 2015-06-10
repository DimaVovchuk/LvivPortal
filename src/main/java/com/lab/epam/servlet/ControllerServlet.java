package com.lab.epam.servlet;

import com.lab.epam.command.Command;
import com.lab.epam.command.CommandFactory;

import javax.servlet.RequestDispatcher;
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

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        Command command = CommandFactory.createCommand(request);
        String resultURL = command.execute(request, response);

        if(!resultURL.equals("")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/pages/" + resultURL+".jsp");
            dispatcher.forward(request, response);

        } else {
            response.sendError(500);
        }
    }
}

