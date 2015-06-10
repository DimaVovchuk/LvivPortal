package com.lab.epam.listener;

/**
 * Created by Vasyl on 10.06.2015.
 */
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class Log4jInit implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        String homeDir = event.getServletContext().getRealPath("/");
        File propertiesFile = new File(homeDir,
                "./resources/log4j/log4j.properties");
        System.out.println(propertiesFile.toString());
        PropertyConfigurator.configure(propertiesFile.toString());
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}

