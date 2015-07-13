package com.lab.epam.entity;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

/**
 * Created by Admin on 12.06.2015.
 */
public class Decoder {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public static String decodeStringUtf8(String string){
        String decode = null;
        String newLine= null;
        if (string == null){
            return string;
        }
        else {
            try {
                decode =  new String (string.getBytes("ISO-8859-1"),"UTF-8");
                newLine = decode.replace("'","`");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        loger.info("decodeStringUtf8 method");

        return newLine;
    }

}
