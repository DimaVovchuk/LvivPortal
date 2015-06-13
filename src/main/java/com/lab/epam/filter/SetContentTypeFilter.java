package com.lab.epam.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Dima on 13-Jun-15.
 */
public class SetContentTypeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}