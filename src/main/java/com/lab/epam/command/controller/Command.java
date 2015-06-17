package com.lab.epam.command.controller;

/**
 * Created by Vasyl on 09.06.2015.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    public void execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException;
}

