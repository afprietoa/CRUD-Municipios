package Laboratory.example.demo.service;


import Laboratory.example.demo.model.Cdf;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.model.Viviendas;
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

    @Autowired
    private ViviendasService viviendasService;

    // Crear o actualizar un registro CDF
    public Cdf saveCdf(Cdf cdf) {
        return cdfRepository.save(cdf);
    }

    // Buscar registros por persona
    public List<Cdf> getCdfByPersona(Long idPersona) {
        return cdfRepository.findByPersona_Id(idPersona);
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
            // Buscar y actualizar la entidad Persona asociada
            if (updatedCdf.getPersona() != null && updatedCdf.getPersona().getId() != null) {
                Personas persona = personasService.getPersonaById(updatedCdf.getPersona().getId());
                if (persona == null) {
                    throw new EntityNotFoundException("Persona no encontrada para el ID: " + updatedCdf.getPersona().getId());
                }
                existingCdf.setPersona(persona);
            }

            // Buscar y actualizar la entidad Vivienda asociada
            if (updatedCdf.getVivienda() != null && updatedCdf.getVivienda().getId() != null) {
                Viviendas vivienda = viviendasService.getViviendaById(updatedCdf.getVivienda().getId());
                if (vivienda == null) {
                    throw new EntityNotFoundException("Vivienda no encontrada para el ID: " + updatedCdf.getVivienda().getId());
                }
                existingCdf.setVivienda(vivienda);
            }

            // Actualizar otros campos de Cdf
            existingCdf.setFecha_registro(updatedCdf.getFecha_registro());

            // Guardar y retornar el registro actualizado
            return cdfRepository.save(existingCdf);
        }).orElseThrow(() -> new EntityNotFoundException("CDF no encontrado para el ID: " + id));
    }
}
