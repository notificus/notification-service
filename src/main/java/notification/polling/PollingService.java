package notification.polling;

import notification.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;

import notification.service.message.EmailMessageService;

@Service
public class PollingService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ObjectMapper objectMapper;

    public URL setPollConfiguration(String cip) throws IOException{
        URL jsonUrl = new URL("https://www.gel.usherbrooke.ca/app/api/notes/?cip="+cip);
        //TODO: add more url building features.
        return jsonUrl;
    }

    @Scheduled(initialDelay = 0, fixedRate = (3600000 * 24)) //means each 24h.
    public boolean poll() throws IOException {
        //log.info("polling...");

        objectMapper = new ObjectMapper();
        //TODO: add more user information to the setPollConfiguration.
        Notes[] notes;
        try
        {
           notes = objectMapper.readValue(setPollConfiguration("spip2401"), Notes[].class);
        }
        catch(IOException e)
        {
            notes = new Notes[0];
        }


        if(notes.length == 0)
            return false;

        EmailMessageService emailMessageService = new EmailMessageService();
        emailMessageService.sendMessage("notificusUdes@gmail.com","New note have been added to your file", "This is to inform you that in your file!");
        //log.info("sent email.");
        return true;
    }
}





