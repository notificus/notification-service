package notification.polling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.net.URL;

@Profile("test")
public class PollingServiceTest {

    @InjectMocks
    private Notes[] notes = new Notes[1];

    @Before
    public void setUp(){
        Notes note = new Notes();
        note.setCip("spip2401");
        note.setClassSigil("GIF600");
        note.setCompetence("3");
        note.setNoteType("exam");
        notes[0] = note;
    }

    @Test
    public void testPollingScheduler_ReturnFail() throws IOException {
        PollingService mockPoll = Mockito.spy(new PollingService());
        Mockito.when(mockPoll.requestJsonFromURL(any(URL.class))).thenReturn(new Notes[0]);
        boolean success = mockPoll.poll();
        Assert.assertFalse(success);
    }

    @Test
    public void testPollingScheduler_ReturnSuccess() throws IOException{
        PollingService mockPoll = Mockito.spy(new PollingService());
        Mockito.when(mockPoll.requestJsonFromURL(any(URL.class))).thenReturn(notes);
        boolean success = mockPoll.poll();
        Assert.assertTrue(success);
    }
}