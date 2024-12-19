package Laboratory.example.demo.DTO;

import Laboratory.example.demo.model.Personas;

import java.util.Date;

public class PersonaDTO {
    private Long id;
    private String nombre;
    private String sexo;
    private Date fechaNac;
    private String telefono;
    private Long viviendaId;


    public PersonaDTO() {
    }

    // Constructor para simplificar el mapeo
    public PersonaDTO(Personas persona) {
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.sexo = persona.getSexo();
        this.fechaNac = persona.getFechaNac() != null ? persona.getFechaNac() : null;
        this.telefono = persona.getTelefono();
        this.viviendaId = persona.getViviendaActual() != null ? persona.getViviendaActual().getId(): null;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Date getFechaNac() { return fechaNac; }
    public void setFechaNac(Date fechaNac) { this.fechaNac = fechaNac; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Long getViviendaId() { return viviendaId; }
    public void setViviendaId(Long viviendaDireccion) { this.viviendaId = viviendaDireccion; }
}
