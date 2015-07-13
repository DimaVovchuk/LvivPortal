package com.lab.epam.smtp;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Dima on 31-May-15.
 */
public class SendEmail {
    private static Sender sender;
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public static void sender(String subject, String text, String toEmail) {
        Properties props = new Properties();
        InputStream stream = null;
            try {
                ClassLoader classLoader =SendEmail.class.getClassLoader();
                File file = new File(classLoader.getResource("persistent.xml").getFile());
                stream = new FileInputStream(file);
                props.loadFromXML(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        sender = new Sender(props.getProperty("email.login"), props.getProperty("email.password"));
        loger.info("sender method");
        sender.send(subject, text, toEmail);
    }

}
