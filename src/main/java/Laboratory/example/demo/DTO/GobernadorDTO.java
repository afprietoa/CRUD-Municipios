package Laboratory.example.demo.DTO;

import Laboratory.example.demo.model.Gobernadores;


public class GobernadorDTO {

    private Long id;
    private String nombre;
    private MunicipioDTO municipio;

    // Constructor para convertir desde la entidad
    public GobernadorDTO(Gobernadores gobernador) {
        this.id = gobernador.getId();
        this.municipio = gobernador.getMunicipio() != null ? new MunicipioDTO(gobernador.getMunicipio()) : null;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public MunicipioDTO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }
}
