package com.lab.epam.command.page.index;

import com.lab.epam.command.controller.Command;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ������� on 10.06.2015.
 */
public class IndexCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command IndexCommand.");
        request.setAttribute("active_home", "active");
        request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
    }
}
