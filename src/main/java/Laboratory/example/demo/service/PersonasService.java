package Laboratory.example.demo.service;


import Laboratory.example.demo.DTO.PersonaDTO;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.model.Viviendas;
import Laboratory.example.demo.repository.PersonasRepository;
import Laboratory.example.demo.repository.ViviendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonasService {

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private ViviendasRepository viviendasRepository;

    // Crear o actualizar una persona
    public Personas savePersona(Personas persona) {

        return personasRepository.save(persona);
    }

    // Obtener una persona por ID
    public Personas getPersonaById(Long id) {
        return personasRepository.findById(id).orElse(null);
    }

    // Eliminar una persona
    public void deletePersona(Long id) {
        personasRepository.deleteById(id);
    }

    // Buscar personas por nombre
    public List<Personas> getPersonasByNombre(String nombre) {
        return personasRepository.findByNombre(nombre);
    }

    // Buscar personas por sexo
    public List<Personas> getPersonasBySexo(String sexo) {
        return personasRepository.findBySexo(sexo);
    }

    // Buscar personas nacidas después de una fecha específica
    public List<Personas> getPersonasNacidasDespues(Date fecha) {
        return personasRepository.findByFechaNac(fecha);
    }

    // Obtener personas sin vivienda actual
    public List<Personas> getPersonasSinVivienda() {
        return personasRepository.findPersonasSinVivienda();
    }

    public Personas updatePersonas(Long id, Personas updatedPersona) {
        // Buscar la persona existente por ID
        Personas existingPersona = personasRepository.findById(id).orElse(null);
        if (existingPersona == null) {
            return null; // Si no existe, retornar null
        }

        // Actualizar los campos que no sean null
        if (updatedPersona.getTipo_doc() != null) {
            existingPersona.setTipo_doc(updatedPersona.getTipo_doc());
        }
        if (updatedPersona.getNombre() != null) {
            existingPersona.setNombre(updatedPersona.getNombre());
        }
        if (updatedPersona.getFechaNac() != null) {
            existingPersona.setFechaNac(updatedPersona.getFechaNac());
        }
        if (updatedPersona.getSexo() != null) {
            existingPersona.setSexo(updatedPersona.getSexo());
        }
        if (updatedPersona.getTelefono() != null) {
            existingPersona.setTelefono(updatedPersona.getTelefono());
        }

        // Manejar la relación con Viviendas (si se proporciona)
        if (updatedPersona.getViviendaActual() != null) {
            Long viviendaId = updatedPersona.getViviendaActual().getId();
            Viviendas vivienda = viviendasRepository.findById(viviendaId).orElse(null);
            if (vivienda != null) {
                existingPersona.setViviendaActual(vivienda);
            }
        }

        // Guardar los cambios en la base de datos
        return personasRepository.save(existingPersona);
    }

    public List<Personas> getAllPersonas() {
        return personasRepository.findAll();
    }
}