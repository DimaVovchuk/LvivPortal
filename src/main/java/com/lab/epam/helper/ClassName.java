package com.lab.epam.helper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Vasyl on 10.06.2015.
 */
public class ClassName {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
//            loger.info("Get class name: " + e.getStackTrace()[1].getClassName());
            return e.getStackTrace()[1].getClassName();
        }
    }
}