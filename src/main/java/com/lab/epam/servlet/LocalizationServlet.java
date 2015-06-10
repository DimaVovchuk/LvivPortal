package com.lab.epam.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("lang");
        Locale locale = new Locale(language);
        HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("bundle/bundle", locale);
        session.setAttribute("bundle", bundle);
        Cookie cookie = new Cookie("lang", language);
        response.addCookie(cookie);
        response.sendRedirect("");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}