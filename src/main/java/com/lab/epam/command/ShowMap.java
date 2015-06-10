package com.lab.epam.command;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vasyl on 09.06.2015.
 */
public class ShowMap implements Command{
        private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        loger.info("Command ShowMap.");
        return "map";
    }
}
