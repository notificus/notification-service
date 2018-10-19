package notification;

import java.util.Properties;

import java.util.Date;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public static boolean sendEmail(String destination, String subject, String content ) {
        final String fromEmail = "notificusUdes@gmail.com";
        final String password = "NotificusAdmin111";

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        if(createEmail(session,subject,content, destination))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private static boolean createEmail(Session session, String subject, String content, String destination) {
        try {
            final String toEmail = destination;

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("notificusUdes@gmail.com", "Notificus"));

            msg.setReplyTo(InternetAddress.parse("notificusUdes@gmail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(content, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

