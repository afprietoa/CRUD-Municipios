package Laboratory.example.demo.repository;


import Laboratory.example.demo.model.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonasRepository extends JpaRepository<Personas, Long> {

    // Buscar personas por nombre exacto
    List<Personas> findByNombre(String nombre);

    // Buscar personas por sexo
    List<Personas> findBySexo(String sexo);

    // Buscar personas nacidas después de una fecha específica
    List<Personas> findByFechaNac(Date fecha);

    // Consultar personas con vivienda actual no asignada
    @Query("SELECT p FROM Personas  p WHERE p.viviendaActual IS NULL")
    List<Personas> findPersonasSinVivienda();
}