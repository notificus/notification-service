package notification.polling;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PollingService {

    private static final Logger log = LoggerFactory.getLogger(Application.class); //Console Debugger. It will show in spring console.

    @Scheduled(cron = "0 0 0 0/24 * ?") //means each 24h.
    public boolean poll() throws IOException {

        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        URL jsonUrl = new URL("https://www.gel.usherbrooke.ca/app/api/nouvelles/?cip=spip2401&date=" + dateFormat.format(new Date()));
        ObjectMapper mapper = new ObjectMapper();
        Properties[] properties = mapper.readValue(jsonUrl,Properties[].class);

        for (Properties p : properties) {
            log.info("id:" + p.getId() + ", nouvelle:"+p.getNouvelle()+", creator:"+p.getCreateur());
        }

        if(properties.length == 0)
            return true;
        return false;
    }
}





