package ar.edu.utn.ba.ddsi.climalert;

import ar.edu.utn.ba.ddsi.climalert.config.WeatherApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(WeatherApiProperties.class)
public class ClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimalertApplication.class, args);
	}
}