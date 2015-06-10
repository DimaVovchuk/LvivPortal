package com.lab.epam.command;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Галинка on 10.06.2015.
 */
public class Index implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        loger.info("Command Index.");
        return "index";
    }
}
