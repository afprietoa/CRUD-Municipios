package Laboratory.example.demo.DTO;

import Laboratory.example.demo.model.Cdf;
import Laboratory.example.demo.model.Personas;

public class CdfDTO {

    private Long id;


    private Long persona;

    private Long vivienda;

    private String fecha_registro;

    public CdfDTO(Cdf cdf) {
        this.id = cdf.getId();
        this.persona = cdf.getPersona() != null ? cdf.getPersona().getId() : null;
        this.vivienda = cdf.getVivienda() != null ? cdf.getVivienda().getId() : null;
        this.fecha_registro = cdf.getFecha_registro();
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPersona() { return persona; }
    public void setPersona(Personas persona) { this.persona = persona.getId(); }

    public Long getVivienda() { return vivienda; }
    public void setVivienda(Long vivienda) { this.vivienda = vivienda; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro) { this.fecha_registro = fecha_registro; }
}

