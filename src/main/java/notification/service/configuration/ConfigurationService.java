package notification.service.configuration;

import org.springframework.web.client.HttpClientErrorException;

public interface ConfigurationService {
    Configuration getConfiguration(String cip) throws HttpClientErrorException;
}
