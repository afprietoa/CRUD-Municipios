package Laboratory.example.demo.repository;

import Laboratory.example.demo.model.Municipios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Long> {

    // Buscar un municipio por nombre exacto
    Municipios findByNombre(String nombre);

    // Listar municipios ordenados por nombre
    List<Municipios> findAllByOrderByNombreAsc();
}