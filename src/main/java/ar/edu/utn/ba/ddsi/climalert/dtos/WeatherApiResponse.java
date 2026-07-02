package ar.edu.utn.ba.ddsi.climalert.client.dto;

import ar.edu.utn.ba.ddsi.climalert.dtos.CurrentDto;

public record WeatherApiResponse(
        CurrentDto current
) {
}