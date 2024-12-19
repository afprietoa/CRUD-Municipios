package Laboratory.example.demo.repository;


import Laboratory.example.demo.model.Gobernadores;
import Laboratory.example.demo.model.Municipios;
import Laboratory.example.demo.model.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GobernadoresRepository extends JpaRepository<Gobernadores, Long> {

    // Buscar gobernadores por ID de municipio
    List<Gobernadores> findByMunicipio(Municipios idMunicipio);

    // Buscar gobernador asignado a una persona específica
    Gobernadores findByPersona(Personas idPersona);
}

