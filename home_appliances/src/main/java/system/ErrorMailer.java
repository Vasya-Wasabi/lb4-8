package system;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

/**
 * Sends error reports by email using the Gmail SMTP server.
 * Automatically called from LogService when an error occurs.
 * Runs asynchronously in a separate thread.
 */
public class ErrorMailer {

    /** Sender email credentials and recipient address. */
    private static final String USERNAME = "SenderEmail";
    private static final String PASSWORD = "Password";
    private static final String TO = "RecipientEmail";

    /**
     * Sends an error message with stack trace to the configured email.
     *
     * @param msg       short description of the error
     * @param exception exception to include in the message body
     */
    public static void sendError(String msg, Throwable exception) {
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");

        Session mailSession = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        new Thread(() -> {
            try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            message.setSubject(msg);

            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));
            String exceptionText = sw.toString();

            message.setText(exceptionText);
            Transport.send(message);

            LogService.info("Error email sent successfully.");
        } catch (MessagingException e) {
                LogService.warn("Failed to send error email: " + e.getMessage());
            }
        }).start();
    }

}