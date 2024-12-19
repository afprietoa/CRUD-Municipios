package Laboratory.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "viviendas")
public class Viviendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100, message = "Address must not exceed 100 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9\\s#\\-/,\\.]+$",
            message = "Address can only contain letters, numbers, spaces, and the characters #, -, /, , and ."
    )
    private String direccion;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10, message = "Capacity must not exceed 10")
    private Integer capacidad;

    @NotNull(message = "Levels cannot be null")
    @Min(value = 1, message = "Levels must be at least 1")
    @Max(value = 6, message = "Levels must not exceed 6")
    private Integer niveles;

    @ManyToOne
    @JoinColumn(name = "id_municipio")

    private Municipios municipio;

    @OneToMany(mappedBy = "viviendaActual")
    @JsonManagedReference
    private List<Personas> habitantes;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public Integer getNiveles() { return niveles; }
    public void setNiveles(Integer niveles) { this.niveles = niveles; }

    public Municipios getMunicipio() { return municipio; }
    public void setMunicipio(Municipios municipio) { this.municipio = municipio; }

    public List<Personas> getHabitantes() { return habitantes; }
    public void setHabitantes(List<Personas> habitantes) { this.habitantes = habitantes; }
}