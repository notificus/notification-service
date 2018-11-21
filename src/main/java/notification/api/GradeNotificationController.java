package notification.api;

import notification.service.configuration.Configuration;
import notification.service.configuration.ConfigurationService;
import notification.service.notification.Notification;
import notification.service.notification.email.EmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class GradeNotificationController {
    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    Logger logger = LoggerFactory.getLogger(GradeNotificationController.class);

    @RequestMapping(value = Routes.GRADES_ROUTE, method = POST)
    public ResponseEntity<String> createGradeNotification(@RequestBody GradeNotificationContract gradeNotificationContract) {
        try {
            Configuration configuration = configurationService.getConfiguration(gradeNotificationContract.getCip());

            if (configuration.getEmailEnabled()) {
                emailNotificationService.sendNotification(new Notification(
                        configuration.getEmails(),
                        "Grade",
                        "You have a new grade for course: " + gradeNotificationContract.getCourse(),
                        LocalDateTime.now()));
            }

            return ResponseEntity.created(URI.create("")).build();
        } catch (HttpClientErrorException e) {
            logger.error("Failed to get user configuration for cip: " + gradeNotificationContract.getCip());
            return ResponseEntity.accepted().build();
        }
    }
}
