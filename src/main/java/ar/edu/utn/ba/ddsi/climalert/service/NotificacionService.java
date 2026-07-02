package ar.edu.utn.ba.ddsi.climalert.service;

import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);

    private static final String[] DESTINATARIOS = {
            "admin@clima.com",
            "emergencias@clima.com",
            "meteorologia@clima.com"
    };

    private final JavaMailSender mailSender;

    public NotificacionService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarAlerta(RegistroClimatico registro) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(DESTINATARIOS);
        mensaje.setSubject("Alerta climatica critica");
        mensaje.setText(construirCuerpo(registro));

        try {
            mailSender.send(mensaje);
            log.info("Alerta enviada correctamente a {} destinatarios", DESTINATARIOS.length);
        } catch (MailException e) {
            log.error("No se pudo enviar el mail de alerta: {}", e.getMessage());
        }
    }

    private String construirCuerpo(RegistroClimatico registro) {
        return """
                Se detectaron condiciones climaticas criticas.

                Temperatura: %.1f C
                Humedad: %d%%
                Fecha y hora: %s
                """.formatted(
                registro.getTemperatura(),
                registro.getHumedad(),
                registro.getFechaHora()
        );
    }
}