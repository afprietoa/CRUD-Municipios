package Laboratory.example.demo.service;


import Laboratory.example.demo.model.Gobernadores;
import Laboratory.example.demo.model.Municipios;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.repository.GobernadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GobernadoresService {

    @Autowired
    private GobernadoresRepository gobernadoresRepository;

    // Crear o actualizar un gobernador
    public Gobernadores saveGobernador(Gobernadores gobernador) {
        return gobernadoresRepository.save(gobernador);
    }

    // Buscar gobernador por persona
    public Gobernadores getGobernadorByPersona(Personas idPersona) {
        return gobernadoresRepository.findByPersona(idPersona);
    }

    // Buscar gobernadores por municipio
    public List<Gobernadores> getGobernadoresByMunicipio(Municipios idMunicipio) {
        return gobernadoresRepository.findByMunicipio(idMunicipio);
    }

    public void deleteGobernadores(Long id) {
        gobernadoresRepository.deleteById(id);
    }

    public Gobernadores updateGobernadores(Long id, Gobernadores updatedGobernador) {
        return gobernadoresRepository.findById(id).map(existingGobernador -> {
            existingGobernador.setPersona(updatedGobernador.getPersona());
            existingGobernador.setMunicipio(updatedGobernador.getMunicipio());
            existingGobernador.setFecha_registro(updatedGobernador.getFecha_registro());
            return gobernadoresRepository.save(existingGobernador);
        }).orElse(null);
    }

    public List<Gobernadores> getAllGobernadores() {
        return gobernadoresRepository.findAll();
    }

}
