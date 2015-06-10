package com.lab.epam.command;

/**
 * Created by Vasyl on 09.06.2015.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public String execute(HttpServletRequest request,
                          HttpServletResponse response);
}

