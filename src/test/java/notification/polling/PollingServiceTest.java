package notification.polling;

import notification.polling.PollingSerive;
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
    public void testSuccessPollingScheduler() throws IOException {

        boolean success = pollingService.poll();
        Assert.assertEquals(true,success);
    }

    public void testFailurePollingScheduler() throws IOException{
        boolean success = pollingService.poll();
        Assert.assertEquals(false,success);
    }
}