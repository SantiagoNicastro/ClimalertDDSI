package ar.edu.utn.ba.ddsi.climalert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentDto(
        @JsonProperty("temp_c") Double tempC,
        Integer humidity
) {
}