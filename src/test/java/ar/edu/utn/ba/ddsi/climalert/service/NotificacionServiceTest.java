package ar.edu.utn.ba.ddsi.climalert.notificacion;

import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import ar.edu.utn.ba.ddsi.climalert.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class NotificacionServiceTest {

    @Test
    void enviaMailATresDestinatariosConDetalleDelClima() {
        JavaMailSender mailSenderMock = mock(JavaMailSender.class);
        NotificacionService service = new NotificacionService(mailSenderMock);

        RegistroClimatico registroCritico = new RegistroClimatico(38.5, 70, LocalDateTime.now());

        service.enviarAlerta(registroCritico);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSenderMock).send(captor.capture());

        SimpleMailMessage mensajeEnviado = captor.getValue();

        assertArrayEquals(
                new String[]{"admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com"},
                mensajeEnviado.getTo()
        );
        assertTrue(mensajeEnviado.getText().contains("38.5"));
        assertTrue(mensajeEnviado.getText().contains("70"));
    }
}