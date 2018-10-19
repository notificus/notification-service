package notification;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class EmailTest {


    @Test
    public void testSendEmailSuccess() {

        boolean success = Email.sendEmail("notificusUdes@gmail.com","test", "test");
        Assert.assertEquals(true,success);
    }

}