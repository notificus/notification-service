package notification.service.message;

import java.util.Properties;

import java.util.Date;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailMessageService implements MessageService {

    @Override
    public boolean sendMessage(String destination, String subject, String content ) {

        boolean success = false;
        final String fromEmail = "notificusUdes@gmail.com";
        final String password = "NotificusAdmin111";

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
        try {
            MimeMessage message = createMessage(session,subject,content, destination);
            if(message!=null) {
                Transport.send(message);
                success = true;
            }
        }
        catch (Exception e) {
                e.printStackTrace();
        }

        return success;
    }

    public MimeMessage createMessage(Session session, String subject, String content, String destination) {

        MimeMessage msg = new MimeMessage(session);
        try {

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("notificusUdes@gmail.com", "Notificus"));

            msg.setReplyTo(InternetAddress.parse("notificusUdes@gmail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(content, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination, false));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}

