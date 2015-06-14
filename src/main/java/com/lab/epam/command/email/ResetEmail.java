package com.lab.epam.command.email;

import com.lab.epam.command.Command;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dima on 15-Jun-15.
 */
public class ResetEmail implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command IndexCommand.");
        request.getRequestDispatcher("/views/pages/ResetEmail.jsp").forward(request, response);
    }
}
