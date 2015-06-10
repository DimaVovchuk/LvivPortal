package com.lab.epam.helper;

/**
 * Created by Vasyl on 10.06.2015.
 */
public class ClassName {
    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}