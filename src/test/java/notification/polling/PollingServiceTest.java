package notification.polling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;

import java.io.IOException;

@Profile("test")
public class PollingServiceTest {

    @Autowired
    PollingService pollingService;

    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private Notes[] notes;

    @Test
    public void testPollingScheduler_ReturnFail() throws IOException {

        objectMapper = Mockito.mock(ObjectMapper.class);
        when(objectMapper.readValue(eq(""),Notes[].class)).thenReturn(new Notes[0]);
        boolean success = pollingService.poll();
        Assert.assertEquals(false, success);
    }

    @Test
    public void testPollingScheduler_ReturnSuccess() throws IOException{
        objectMapper = Mockito.mock(ObjectMapper.class);
        when(objectMapper.readValue(eq(""),Notes[].class)).thenReturn(notes);
        boolean success = pollingService.poll();
        Assert.assertFalse(success);
    }
}