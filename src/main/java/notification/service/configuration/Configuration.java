package notification.service.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Configuration {
    @JsonProperty("emailEnabled")
    private Boolean emailEnabled;
    @JsonProperty("emails")
    private List<String> emails;

    @JsonCreator
    public Configuration(@JsonProperty("emailEnabled") Boolean emailEnabled, @JsonProperty("emails") List<String> emails) {
        this.emailEnabled = emailEnabled;
        this.emails = emails;
    }

    public Boolean getEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(Boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
