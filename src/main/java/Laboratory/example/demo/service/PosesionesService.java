package Laboratory.example.demo.service;


import Laboratory.example.demo.DTO.PosesionesDTO;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.model.Posesiones;
import Laboratory.example.demo.model.Viviendas;
import Laboratory.example.demo.repository.PersonasRepository;
import Laboratory.example.demo.repository.PosesionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosesionesService {

    @Autowired
    private PosesionesRepository posesionesRepository;

    @Autowired
    private PersonasRepository personasRepository;


    // Crear o actualizar una posesión
    public Posesiones savePosesion(Posesiones posesion) {
        return posesionesRepository.save(posesion);
    }

    // Obtener posesiones de una persona
    public List<Posesiones> getPosesionesByPersona(Personas idPersona) {
        return posesionesRepository.findByPersona(idPersona);
    }

    // Obtener posesiones de una vivienda
    public List<Posesiones> getPosesionesByVivienda(Viviendas idVivienda) {
        return posesionesRepository.findByVivienda(idVivienda);
    }

    // Contar todas las posesiones
    public long countPosesiones() {
        return posesionesRepository.count();
    }

    public void deletePosesiones(Long id) {
        posesionesRepository.deleteById(id);
    }

    public Posesiones updatePosesiones(Long id, PosesionesDTO posesionesDTO) {
        // Buscar la posesión existente por ID
        Posesiones existingPosesion = posesionesRepository.findById(id).orElse(null);
        if (existingPosesion == null) {
            return null; // Retorna null si no existe
        }

        // Validar y actualizar la persona
        Personas persona = personasRepository.findById(posesionesDTO.getPersonaId()).orElse(null);

        if (persona == null) {
            throw new IllegalArgumentException("La persona no existe.");
        }
        existingPosesion.setPersona(persona);
        return existingPosesion;
    }


    public List<Posesiones> getAllPosesiones() {
        return posesionesRepository.findAll();
    }
}
