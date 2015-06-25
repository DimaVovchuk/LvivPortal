package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vasyl on 25.06.2015.
 */
public class AddNewPlaceCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/pages/add_new_palce.jsp").forward(request, response);
    }
}
