package notification.service.notification.email;

import notification.service.notification.Notification;
import notification.service.notification.NotificationService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailNotificationService implements NotificationService {
    private final String NOTIFICUS_EMAIL = "notificusUdes@gmail.com";
    private final String NOTIFICUS_EMAIL_PASSWORD = "NotificusAdmin111";

    @Override
    public Boolean sendNotification(Notification notification) {
        Boolean success = false;

        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.host", "smtp.gmail.com");
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

        Authenticator emailAuthenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(NOTIFICUS_EMAIL, NOTIFICUS_EMAIL_PASSWORD);
            }
        };

        Session session = Session.getInstance(emailProperties, emailAuthenticator);
        try {
            MimeMessage email = buildEmail(session, notification);
            if (email != null) {
                Transport.send(email);
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    private MimeMessage buildEmail(Session session, Notification notification) {
        MimeMessage email = new MimeMessage(session);

        try {
            InternetAddress notificusInternetAddress = new InternetAddress(NOTIFICUS_EMAIL);
            email.addHeader("Content-type", "text/HTML; charset=UTF-8");
            email.addHeader("format", "flowed");
            email.addHeader("Content-Transfer-Encoding", "8bit");

            email.setFrom(notificusInternetAddress);
            email.setReplyTo(new InternetAddress[]{notificusInternetAddress});
            email.setSubject(notification.getType() + " notification from Notificus", "UTF-8");
            email.setText(notification.getData(), "UTF-8");
            email.setSentDate(new Date());
            email.setRecipients(Message.RecipientType.TO, StringUtils.join(notification.getDestinations(), ','));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }
}


