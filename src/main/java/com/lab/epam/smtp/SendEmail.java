package com.lab.epam.smtp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Dima on 31-May-15.
 */
public class SendEmail {
    private static Sender sender;

    public static void sender(String subject, String text, String toEmail) {
        Properties props = new Properties();
        InputStream stream = null;
            try {
                stream = new FileInputStream("D:\\JAVA\\Git\\LvivPortal\\src\\main\\resources\\persistent.xml");
                props.loadFromXML(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        sender = new Sender(props.getProperty("email.login"), props.getProperty("email.password"));
        sender.send(subject, text, toEmail);
    }

}
