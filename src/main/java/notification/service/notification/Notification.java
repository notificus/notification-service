package notification.service.notification;

import java.time.LocalDateTime;
import java.util.List;

public class Notification {
    private List<String> destinations;
    private String type;
    private String data;
    private LocalDateTime dateTime;

    public Notification(List<String> destinations, String type, String data, LocalDateTime dateTime) {
        this.destinations = destinations;
        this.type = type;
        this.data = data;
        this.dateTime = dateTime;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
