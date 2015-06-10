package com.lab.epam.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WelcomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Locale locale = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lang")) {
                    locale = new Locale(cookie.getValue());
                }
            }
        }
        if (locale == null) {
            locale = new Locale("EN");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("/resources/localization/bundle", locale);
        HttpSession session = request.getSession();
        //session.setAttribute("bundle", bundle);
        response.setContentType("text/html; charset=windows-1251");
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}