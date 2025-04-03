package utils;

import java.io.FileInputStream;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {
    public static void sendEmail(String recipient, String subject, String content) {
        try {
            // Load properties from file
            Properties configProps = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            configProps.load(fis);

            String senderEmail = configProps.getProperty("email.sender");
            String senderPassword = configProps.getProperty("email.password");

            // Email Server Properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Create a mail session
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // Create and send email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email sent successfully to " + recipient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
