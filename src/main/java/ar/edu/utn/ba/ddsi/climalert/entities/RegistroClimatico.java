package ar.edu.utn.ba.ddsi.climalert.entities;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegistroClimatico {      //entendi como que los unicos componentes climaticos para esta itercion (y para notificar
                                        //son humedad y temperatura

    private final Double temperatura;
    private final Integer humedad;
    private final LocalDateTime fechaHora;

    public RegistroClimatico(Double temperatura, Integer humedad, LocalDateTime fechaHora) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fechaHora = fechaHora;
    }

    public boolean esCondicionCritica() {
        return temperatura > 35 && humedad > 60;
    }
}