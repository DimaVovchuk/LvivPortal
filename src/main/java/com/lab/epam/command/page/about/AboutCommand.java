package com.lab.epam.command.page.about;

import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Oleguk on 03.07.2015.
 */
public class AboutCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loger.info("Command AboutCommand");
        request.getRequestDispatcher("/views/pages/about.jsp").forward(request, response);
    }
}
