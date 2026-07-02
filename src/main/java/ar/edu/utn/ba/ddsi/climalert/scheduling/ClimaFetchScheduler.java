package ar.edu.utn.ba.ddsi.climalert.scheduling;

import ar.edu.utn.ba.ddsi.climalert.clients.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.exceptions.WeatherApiException;
import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import ar.edu.utn.ba.ddsi.climalert.repository.RegistroClimaticoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaFetchScheduler {

    private static final Logger log = LoggerFactory.getLogger(ClimaFetchScheduler.class);
    private static final long CINCO_MINUTOS_EN_MS = 5 * 60 * 1000;

    private final WeatherApiClient weatherApiClient;
    private final RegistroClimaticoRepository repository;

    public ClimaFetchScheduler(WeatherApiClient weatherApiClient, RegistroClimaticoRepository repository) {
        this.weatherApiClient = weatherApiClient;
        this.repository = repository;
    }

    @Scheduled(fixedRate = CINCO_MINUTOS_EN_MS)
    public void obtenerYGuardarClimaActual() {
        try {
            RegistroClimatico registro = weatherApiClient.obtenerClimaActual();
            repository.guardar(registro);
            log.info("Registro climatico guardado: temp={}, humedad={}", registro.getTemperatura(), registro.getHumedad());
        } catch (WeatherApiException e) {
            log.error("No se pudo actualizar el clima en este ciclo: {}", e.getMessage());
        }
    }
}