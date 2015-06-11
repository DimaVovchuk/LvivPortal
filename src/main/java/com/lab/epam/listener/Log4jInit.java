package com.lab.epam.listener;

/**
 * Created by Vasyl on 10.06.2015.
 */

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class Log4jInit implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}

