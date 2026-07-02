package ar.edu.utn.ba.ddsi.climalert.repository;

import ar.edu.utn.ba.ddsi.climalert.entities.RegistroClimatico;

import java.util.List;

public interface RegistroClimaticoRepository {
    void guardar(RegistroClimatico registro);
    RegistroClimatico obtenerUltimo();
    List<RegistroClimatico> obtenerTodos();
}