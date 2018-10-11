package notification.polling;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PollingServiceTest {

    @Autowired
    PollingService pollingService;

    @Test
    public void poll() {
        boolean success = pollingService.poll();
        Assert.assertEquals(true,success);
    }
}