package com.lab.epam.smtp;

/**
 * Created by Dima on 31-May-15.
 */
public class SendEmail {

    private static Sender sender = new Sender("mail.for.blablabla@gmail.com", "987456321dima");

    public static void sender(String subject, String text, String toEmail) {
        sender.send(subject, text, "", toEmail);
    }

}
