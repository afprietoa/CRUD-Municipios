package Laboratory.example.demo.DTO;

import Laboratory.example.demo.model.Posesiones;

public class PosesionesDTO {

    public Long id;
    public Long personaId;      // ID de la persona asociada
    private String personaNombre; // Nombre de la persona
    private Long viviendaId;      // ID de la vivienda asociada
    private String viviendaDireccion; // Dirección de la vivienda
    private String fechaPosesion; // Fecha de posesión


    public PosesionesDTO() {
    }


    public PosesionesDTO(Long personaId, Long viviendaId, String fechaPosesion) {
        this.personaId = personaId;
        this.viviendaId = viviendaId;
        this.fechaPosesion = fechaPosesion;
    }

    // Constructor basado en la entidad Posesiones
    public PosesionesDTO(Posesiones posesion) {
        this.id = posesion.getId();
        this.personaId = posesion.getPersona().getId();
        this.personaNombre = posesion.getPersona().getNombre();
        this.viviendaId = posesion.getVivienda().getId();
        this.viviendaDireccion = posesion.getVivienda().getDireccion();
        this.fechaPosesion = posesion.getFecha_posesion();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPersonaId() { return personaId; }
    public void setPersonaId(Long personaId) { this.personaId = personaId; }

    public String getPersonaNombre() { return personaNombre; }
    public void setPersonaNombre(String personaNombre) { this.personaNombre = personaNombre; }

    public Long getViviendaId() { return viviendaId; }
    public void setViviendaId(Long viviendaId) { this.viviendaId = viviendaId; }

    public String getViviendaDireccion() { return viviendaDireccion; }
    public void setViviendaDireccion(String viviendaDireccion) { this.viviendaDireccion = viviendaDireccion; }

    public String getFechaPosesion() { return fechaPosesion; }
    public void setFechaPosesion(String fechaPosesion) { this.fechaPosesion = fechaPosesion; }
}