package Laboratory.example.demo.repository;


import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.model.Posesiones;
import Laboratory.example.demo.model.Viviendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosesionesRepository extends JpaRepository<Posesiones, Long> {

    // Buscar todas las posesiones de una persona específica
    List<Posesiones> findByPersona(Personas idPersona);

    // Buscar posesiones asociadas a una vivienda específica
    List<Posesiones> findByVivienda(Viviendas idVivienda);

    // Contar todas las posesiones existentes
    long count();
}
