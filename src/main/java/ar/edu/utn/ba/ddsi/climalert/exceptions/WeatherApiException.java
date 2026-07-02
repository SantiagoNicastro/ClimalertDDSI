package ar.edu.utn.ba.ddsi.climalert.exceptions;

public class WeatherApiException extends RuntimeException {

    public WeatherApiException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}