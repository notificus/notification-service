package notification.polling;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PollingService {

    public URL setGetConfiguration(String cip) throws IOException{
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        URL jsonUrl = new URL("https://www.gel.usherbrooke.ca/app/api/nouvelles/?cip="+cip+"&date="+dateFormat.format(new Date()));

        return jsonUrl;
    }

    @Scheduled(cron = "0 0 0 0/24 * ?") //means each 24h.
    public boolean poll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Notes[] properties = mapper.readValue( setGetConfiguration("spip2401"), Notes[].class);

        if(properties.length == 0)
            return false;
        return true;
    }
}





