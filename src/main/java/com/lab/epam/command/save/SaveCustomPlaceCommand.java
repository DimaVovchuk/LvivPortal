package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vasyl on 28.06.2015.
 */
public class SaveCustomPlaceCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String lat = request.getParameter("info");
        String lot = request.getParameter("info1");

        System.out.println("lat " + lat);
        System.out.println("lot " + lot);
    }
}
