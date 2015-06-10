package com.lab.epam.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Dima on 10-Jun-15.
 */
public class LocaleCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("lang");
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("locale/messages", locale);
        HttpSession session = request.getSession();
        session.setAttribute("bundle", bundle);
        Cookie cookie = new Cookie("lang", lang);
        response.addCookie(cookie);
        return "locale";
    }
}
