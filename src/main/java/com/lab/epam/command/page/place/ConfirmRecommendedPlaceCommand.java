package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ������� on 07.07.2015.
 */
public class ConfirmRecommendedPlaceCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/pages/�onfirmRecommendedPlace.jsp").forward(request, response);
    }
}
