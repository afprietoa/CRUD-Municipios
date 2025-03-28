package Laboratory.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "gobernadores", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_persona", "id_municipio"})})
public class Gobernadores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona", nullable = true, foreignKey = @ForeignKey(name = "FK_gobernadores_persona", value = ConstraintMode.CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (id_persona) REFERENCES personas(id) ON DELETE CASCADE"))
    private Personas persona;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_municipio", nullable = true, foreignKey = @ForeignKey(name = "FK_gobernadores_municipio", value = ConstraintMode.CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (id_municipio) REFERENCES municipios(id) ON DELETE CASCADE"))
    private Municipios municipio;

    @NotBlank(message = "The registration date cannot be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The registration date must follow the format YYYY-MM-DD")
    private String fecha_registro;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Personas getPersona() { return persona; }
    public void setPersona(Personas persona) { this.persona = persona; }

    public Municipios getMunicipio() { return municipio; }
    public void setMunicipio(Municipios municipio) { this.municipio = municipio; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro) { this.fecha_registro = fecha_registro; }
}
