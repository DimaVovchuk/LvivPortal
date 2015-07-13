package com.lab.epam.filter;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Dima on 10-Jun-15.
 */
public class I18NFilter implements Filter {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        if (bundle == null) {
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
            bundle = ResourceBundle.getBundle("localization/bundle", locale);
            session.setAttribute("bundle", bundle);

        }
        loger.info("getAllPlaceImageByPlaceId method");
        session.setAttribute("language", bundle.getLocale().toString());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
