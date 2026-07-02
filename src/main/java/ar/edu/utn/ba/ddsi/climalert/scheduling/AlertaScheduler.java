package ar.edu.utn.ba.ddsi.climalert.scheduling;
import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import ar.edu.utn.ba.ddsi.climalert.service.NotificacionService;
import ar.edu.utn.ba.ddsi.climalert.repository.RegistroClimaticoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertaScheduler {

    private static final long UN_MINUTO_EN_MS = 60 * 1000;

    private final RegistroClimaticoRepository repository;
    private final NotificacionService notificacionService;

    public AlertaScheduler(RegistroClimaticoRepository repository, NotificacionService notificacionService) {
        this.repository = repository;
        this.notificacionService = notificacionService;
    }

    @Scheduled(fixedRate = UN_MINUTO_EN_MS)
    public void analizarUltimoRegistro() {
        RegistroClimatico ultimo = repository.obtenerUltimo();

        if (ultimo == null) {
            return;
        }

        if (ultimo.esCondicionCritica()) {
            notificacionService.enviarAlerta(ultimo);
        }
    }
}