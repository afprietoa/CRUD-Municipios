package Laboratory.example.demo.repository;

import Laboratory.example.demo.model.Cdf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdfRepository extends JpaRepository<Cdf, Long> {

    // Buscar registros de CDF para una persona específica
    //List<Cdf> findByPersona(Long persona);

    // Buscar registros de CDF para una persona específica por su ID
    List<Cdf> findByPersona_Id(Long personaId);

    // Contar todos los registros existentes en CDF
    long count();
}
