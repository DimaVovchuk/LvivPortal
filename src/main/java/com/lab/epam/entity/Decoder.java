package com.lab.epam.entity;

import java.io.UnsupportedEncodingException;

/**
 * Created by Admin on 12.06.2015.
 */
public class Decoder {

    public String decodeStringUtf8(String string){
        String decode = null;
        if (string == null){
            return string;
        }
        else {
            try {
                decode =  new String (string.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return decode;
    }

}
