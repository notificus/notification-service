package notification.polling;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> defined object recieve from note MS
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PollingService {

    private static final Logger log = LoggerFactory.getLogger(Application.class); //Console Debugger. It will show in spring console.

    public URL setGetConfiguration(String msUrl) throws IOException{
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        URL jsonUrl = new URL(msUrl+dateFormat.format(new Date()));

        return jsonUrl;
    }

    @Scheduled(cron = "0 0 0 0/24 * ?") //means each 24h.
    public boolean poll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Notes[] properties = mapper.readValue( setGetConfiguration("https://www.gel.usherbrooke.ca/app/api/nouvelles/?cip=spip2401&date=")
                                                    , Notes[].class);

        if(properties.length == 0)
            return false;
        return true;
    }
}





