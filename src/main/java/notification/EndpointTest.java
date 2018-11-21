package notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification.api.GradeNotificationContract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Service
public class EndpointTest {
    @Value("${server.port}")
    private String port;

    @Value("${test-data}")
    private String testData;

    @Scheduled(initialDelay = 15000, fixedRate = (999999999))
    public void sendRequest() throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(testData);
        GradeNotificationContract[] gradeNotificationContracts = mapper.readValue(new File(testData), GradeNotificationContract[].class);

        for (GradeNotificationContract gradeNotificationContract : gradeNotificationContracts) {
            restTemplate.postForLocation("http://localhost:" + port + "/grades", gradeNotificationContract);
            Thread.sleep(20000);
        }
    }
}
