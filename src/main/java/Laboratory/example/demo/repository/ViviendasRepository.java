package Laboratory.example.demo.repository;

import Laboratory.example.demo.model.Municipios;
import org.springframework.data.jpa.repository.JpaRepository;
import Laboratory.example.demo.model.Viviendas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViviendasRepository extends JpaRepository<Viviendas, Long> {

    // Buscar viviendas por capacidad específica
    List<Viviendas> findByCapacidad(int capacidad);

    // Buscar viviendas en un municipio específico
    List<Viviendas> findByMunicipio(Municipios idMunicipio);

    // Contar viviendas con más de X niveles
    long countByNivelesGreaterThan(int niveles);

    // Consultar viviendas con capacidad mayor a un valor dado
    @Query("SELECT v FROM Viviendas v WHERE v.capacidad > ?1")
    List<Viviendas> findViviendasByCapacidadMayor(int capacidad);
}