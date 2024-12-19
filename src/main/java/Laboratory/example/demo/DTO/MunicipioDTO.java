package Laboratory.example.demo.DTO;

import Laboratory.example.demo.model.Municipios;

import java.util.List;

public class MunicipioDTO {
    private Long id;
    private String nombre;
    private List<ViviendaDTO> viviendas;

    // Constructor con viviendas simplificadas
    public MunicipioDTO(Municipios municipio) {
        this.id = municipio.getId();
        this.nombre = municipio.getNombre();
        this.viviendas = municipio.getViviendas() != null
                ? municipio.getViviendas().stream().map(ViviendaDTO::new).toList()
                : null;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<ViviendaDTO> getViviendas() { return viviendas; }
    public void setViviendas(List<ViviendaDTO> viviendas) { this.viviendas = viviendas; }
}
