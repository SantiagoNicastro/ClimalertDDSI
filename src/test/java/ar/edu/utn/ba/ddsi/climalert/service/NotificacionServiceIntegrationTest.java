package ar.edu.utn.ba.ddsi.climalert.service;

import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import ar.edu.utn.ba.ddsi.climalert.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class NotificacionServiceIntegrationTest {  //este test va a fallar si no se tiene credenciales de Mailtrap

    @Autowired
    private NotificacionService notificacionService;

    @Test
    void enviaAlertaRealAMailtrap() {
        // Este test manda un mail de verdad usando la config de application.yaml.
        // Corre este test y despues revisa tu inbox de Mailtrap para confirmar que llego.
        RegistroClimatico registroForzado = new RegistroClimatico(40.0, 75, LocalDateTime.now());

        notificacionService.enviarAlerta(registroForzado);
    }
}