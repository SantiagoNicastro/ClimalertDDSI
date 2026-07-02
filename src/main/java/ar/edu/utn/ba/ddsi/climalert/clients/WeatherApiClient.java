package ar.edu.utn.ba.ddsi.climalert.clients;

import ar.edu.utn.ba.ddsi.climalert.client.dto.WeatherApiResponse;
import ar.edu.utn.ba.ddsi.climalert.config.WeatherApiProperties;
import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import ar.edu.utn.ba.ddsi.climalert.exceptions.WeatherApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class WeatherApiClient {

    private static final Logger log = LoggerFactory.getLogger(WeatherApiClient.class);

    private final RestTemplate restTemplate;
    private final WeatherApiProperties properties;

    public WeatherApiClient(RestTemplate restTemplate, WeatherApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public RegistroClimatico obtenerClimaActual() {
        String url = String.format(
                "%s/current.json?key=%s&q=%s",
                properties.baseUrl(),
                properties.apiKey(),
                properties.location()
        );

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response == null || response.current() == null) {
                throw new WeatherApiException("WeatherAPI devolvio una respuesta vacia", null);
            }

            return mapearARegistroClimatico(response);

        } catch (RestClientException e) {
            log.error("Error al consultar WeatherAPI: {}", e.getMessage());
            throw new WeatherApiException("No se pudo obtener el clima actual desde WeatherAPI", e);
        }
    }

    private RegistroClimatico mapearARegistroClimatico(WeatherApiResponse response) {
        return new RegistroClimatico(
                response.current().tempC(),
                response.current().humidity(),
                LocalDateTime.now()
        );
    }
}