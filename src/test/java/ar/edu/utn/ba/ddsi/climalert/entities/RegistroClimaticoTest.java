package ar.edu.utn.ba.ddsi.climalert.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistroClimaticoTest {

    @Test
    void esCriticoCuandoSuperaTemperaturaYHumedad() {
        RegistroClimatico registro = new RegistroClimatico(36.0, 61, LocalDateTime.now());

        assertTrue(registro.esCondicionCritica());
    }

    @Test
    void noEsCriticoSiSoloSuperaTemperatura() {
        RegistroClimatico registro = new RegistroClimatico(40.0, 50, LocalDateTime.now());

        assertFalse(registro.esCondicionCritica());
    }

    @Test
    void noEsCriticoSiSoloSuperaHumedad() {
        RegistroClimatico registro = new RegistroClimatico(20.0, 90, LocalDateTime.now());

        assertFalse(registro.esCondicionCritica());
    }

    @Test
    void noEsCriticoConValoresNormales() {
        RegistroClimatico registro = new RegistroClimatico(18.0, 55, LocalDateTime.now());

        assertFalse(registro.esCondicionCritica());
    }

    @Test
    void esCriticoEnLosLimitesExactosNoCuenta() {
        // la condicion usa > estricto, no >=, asi que el limite exacto no dispara alerta
        RegistroClimatico registro = new RegistroClimatico(35.0, 60, LocalDateTime.now());

        assertFalse(registro.esCondicionCritica());
    }
}