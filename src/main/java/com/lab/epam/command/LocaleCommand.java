package com.lab.epam.command;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Dima on 10-Jun-15.
 */
public class LocaleCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameterMap());
        loger.info("Command LocaleCommand.");
        String lang = request.getParameter("lang");
        System.out.println(lang);
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("localization/bundle", locale);
        HttpSession session = request.getSession();
        session.setAttribute("bundle", bundle);
        Cookie cookie = new Cookie("lang", lang);
        response.addCookie(cookie);

    }
}
