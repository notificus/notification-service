package notification.polling;

import notification.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;

import notification.service.message.EmailMessageService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.*;
import java.net.*;
import java.io.*;

@Service
public class PollingService {

    public ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public URL setPollConfiguration(String cip) throws IOException{
        URL jsonUrl = new URL("https://www.gel.usherbrooke.ca/app/api/notes/?cip="+cip);
        //TODO: add more url building features.
        return jsonUrl;
    }

    public JSONArray getJSonArray()
    {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("test.json"));
            return a;
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Scheduled(initialDelay = 0, fixedRate = (3600000 * 24)) //means each 24h.
    public boolean poll(){
        //log.info("polling...");

        //TODO: add more user information to the setPollConfiguration.

        JSONArray a = getJSonArray();
        Gson gson = new Gson();

        Notes[] notes = gson.fromJson(a.toString(), Notes[].class);

        if(notes.length == 0)
            return false;

        EmailMessageService emailMessageService = new EmailMessageService();
        for(int i =0; i < notes.length; i++) {

            try {
                StringBuilder result = new StringBuilder();
                URL url = new URL("http://localhost:8080/users/"+notes[i].getCip()+"/configurations");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                JSONParser parser = new JSONParser();
                JSONObject jsonRequest = (JSONObject) parser.parse(result.toString());
                JSONArray emails = (JSONArray)jsonRequest.get("emails");
                emailMessageService.sendMessage(emails.get(0).toString(), notes[i].getClassSigil(), "Une nouvelle note pour " + notes[i].getClassSigil() + " est disponible.");
            }

            catch(IOException e) {
                e.printStackTrace();
            }
            catch(ParseException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    Notes[] requestJsonFromURL(URL url){
        Notes[] notes;
        try
        {
            notes = objectMapper.readValue(setPollConfiguration("spip2401"), Notes[].class);
        }
        catch(IOException e)
        {
            return new Notes[0];
        }
        return notes;
    }
}





