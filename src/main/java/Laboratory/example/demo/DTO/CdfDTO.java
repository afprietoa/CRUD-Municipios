package Laboratory.example.demo.DTO;

import Laboratory.example.demo.model.Cdf;
import Laboratory.example.demo.model.Personas;

public class CdfDTO {

    private Long id;


    private Long persona;

    private Long cdf;

    private String fecha_registro;

    public CdfDTO(Cdf cdf) {
        this.id = cdf.getId();
        this.persona = cdf.getPersona();
        this.fecha_registro = cdf.getFecha_registro();
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPersona() { return persona; }
    public void setPersona(Personas persona) { this.persona = persona.getId(); }

    public Long getCdf() { return cdf; }
    public void setCdf(Long cdf) { this.cdf = cdf; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro) { this.fecha_registro = fecha_registro; }
}
