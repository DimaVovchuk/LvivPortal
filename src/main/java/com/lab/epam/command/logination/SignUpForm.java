package com.lab.epam.command.logination;

import com.lab.epam.command.controller.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleguk on 02.07.2015.
 */
public class SignUpForm implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String first_name = request.getParameter("first");
        String last_name = request.getParameter("last");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        //System.out.println(first_name);
        //System.out.println(last_name);

        request.setAttribute("first", first_name);
        request.setAttribute("last", last_name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.getRequestDispatcher("/views/modals/login.jsp").forward(request, response);
    }
}
