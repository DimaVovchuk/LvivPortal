package com.lab.epam.smtp;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Dima on 31-May-15.
 */
public class Sender {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    private String username;
    private String password;
    private Properties props;

    public Sender(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "false");
        props.put("mail.smtp.ssl.enable", "true");;
    }

    public void send(String subject, String text, String toEmail) {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);

        Thread myThready = new Thread(new Runnable() {
            public void run()
            {
                try {
                    //from
                    message.setFrom(new InternetAddress(username));
                    //to
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                    //Subject
                    message.setSubject(subject);
                    //Text
                    message.setText(text, "UTF-8", "html");
                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
        loger.info("Send message on email (send method)");
        myThready.start();

    }
}
