package notification.service.message;

public interface MessageService {
    boolean sendMessage(String destination, String subject, String content);
}