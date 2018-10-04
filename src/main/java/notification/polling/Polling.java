package notification.polling;

import com.fasterxml.jackson.databind.ObjectMapper;
import notification.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;

@Service
public class Polling {

    private static final Logger log = LoggerFactory.getLogger(Application.class); //Console Debugger. It will show in spring console.

    @Scheduled(fixedRate = 1000) //in ms
    public void Poll() throws IOException {

        URL jsonUrl = new URL("https://www.gel.usherbrooke.ca/app/api/nouvelles/?cip=spip2401&date=2018-08-01");
        ObjectMapper mapper = new ObjectMapper();
        Properties[] properties = mapper.readValue(jsonUrl,Properties[].class);
        for (Properties p : properties) {
            log.info("id:%d" + p.getId() + ", nouvelle:"+p.getNouvelle()+", creator:"+p.getCreateur());
        }

    }
}

class Properties {
    private int id;
    private String nouvelle;
    private String createur;

    public void setId(int id){
        this.id =id;
    }
    public void setNouvelle(String nouvelle){
        this.nouvelle = nouvelle;
    }
    public void setCreateur(String createur){
        this.createur = createur;
    }

    public int getId(){
        return id;
    }
    public String getNouvelle(){
        return nouvelle;
    }
    public String getCreateur(){
        return createur;
    }
}





