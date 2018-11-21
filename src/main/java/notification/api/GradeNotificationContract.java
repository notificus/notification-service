package notification.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeNotificationContract {
    @JsonProperty("cip")
    private String cip;
    @JsonProperty("course")
    private String course;

    @JsonCreator
    private GradeNotificationContract(@JsonProperty("cip") String cip, @JsonProperty("course") String course) {
        this.cip = cip;
        this.course = course;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
