package ar.edu.utn.ba.ddsi.climalert.repository;

import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class RegistroClimaticoRepositoryInMemory implements ar.edu.utn.ba.ddsi.climalert.repository.RegistroClimaticoRepository {

    private final List<RegistroClimatico> registros = new CopyOnWriteArrayList<>();

    @Override
    public void guardar(RegistroClimatico registro) {
        registros.add(registro);
    }

    @Override
    public RegistroClimatico obtenerUltimo() {
        if (registros.isEmpty()) {
            return null;
        }
        return registros.get(registros.size() - 1);
    }

    @Override
    public List<RegistroClimatico> obtenerTodos() {
        return List.copyOf(registros);
    }
}