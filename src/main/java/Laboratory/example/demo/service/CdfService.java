package Laboratory.example.demo.service;


import Laboratory.example.demo.model.Cdf;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.repository.CdfRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CdfService {

    @Autowired
    private CdfRepository cdfRepository;

    @Autowired
    private PersonasService personasService;

    // Crear o actualizar un registro CDF
    public Cdf saveCdf(Cdf cdf) {
        return cdfRepository.save(cdf);
    }

    // Buscar registros por persona
    public List<Cdf> getCdfByPersona(Long idPersona) {
        return cdfRepository.findByPersona(idPersona);
    }

    // Contar registros CDF
    public long countCdf() {
        return cdfRepository.count();
    }

    public List<Cdf> getAllCdf() {
        return cdfRepository.findAll();
    }

    public boolean deleteCdf(Long id) {
        if (cdfRepository.existsById(id)) {
            cdfRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar un registro CDF
    public Cdf updateCdf(Long id, Cdf updatedCdf) {
        return cdfRepository.findById(id).map(existingCdf -> {
            // Buscar la entidad Persona asociada
            Personas persona = personasService.getPersonaById(updatedCdf.getPersona());
            if (persona == null) {
                throw new EntityNotFoundException("Persona no encontrada para el ID: " + updatedCdf.getPersona());
            }
            // Setear la entidad Persona encontrada
            existingCdf.setPersona(persona);
            existingCdf.setFecha_registro(updatedCdf.getFecha_registro());
            return cdfRepository.save(existingCdf);
        }).orElse(null);
    }
}
