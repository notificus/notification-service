package notification.service.configuration.proxy;

import notification.service.configuration.Configuration;
import notification.service.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
public class ProxyConfigurationService implements ConfigurationService {
    @Value("${configuration-service.url}")
    private String configurationServiceUrl;

    @Value("${configuration-service.port}")
    private String configurationServicePort;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Configuration getConfiguration(String cip) throws HttpClientErrorException {
        String url = format("%s:%s/users/%s/configurations", configurationServiceUrl, configurationServicePort, cip);

        ResponseEntity<Configuration> response = restTemplate.getForEntity(url, Configuration.class);

        if (response.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(response.getStatusCode());
        }

        return response.getBody();
    }
}
