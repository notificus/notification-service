package notification.polling;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Profile("test")
public class PollingServiceTest {

    @Autowired
    PollingService pollingService;

    @Test
    public void testPollingScheduler() throws IOException {

        boolean success = pollingService.poll();
        Assert.assertEquals(true,success);
    }
}