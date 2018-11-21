package notification.api;

import notification.service.configuration.Configuration;
import notification.service.configuration.ConfigurationService;
import notification.service.notification.Notification;
import notification.service.notification.email.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class GradeNotificationController {
    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @RequestMapping(value = Routes.GRADES_ROUTE, method = POST)
    public ResponseEntity<String> createGradeNotification(@RequestBody GradeNotificationContract gradeNotificationContract) {
        Configuration configuration = configurationService.getConfiguration(gradeNotificationContract.getCip());

        if (configuration.getEmailEnabled()) {
            emailNotificationService.sendNotification(new Notification(
                    configuration.getEmails(),
                    "Grade",
                    "You have a new grade for course: " + gradeNotificationContract.getCourse(),
                    LocalDateTime.now()));
        }

        return ResponseEntity.created(URI.create("")).build();
    }
}
