package Laboratory.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "posesiones", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_persona", "id_vivienda"})})
public class Posesiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona", nullable = true, foreignKey = @ForeignKey(name = "FK_posesiones_persona", value = ConstraintMode.CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (id_persona) REFERENCES personas(id) ON DELETE CASCADE"))
    private Personas persona;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_vivienda", nullable = true, foreignKey = @ForeignKey(name = "FK_posesiones_vivienda", value = ConstraintMode.CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (id_vivienda) REFERENCES viviendas(id) ON DELETE CASCADE"))
    private Viviendas vivienda;

    @NotBlank(message = "The possession date cannot be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The possession date must follow the format YYYY-MM-DD")
    private String fecha_posesion;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Personas getPersona() { return persona; }
    public void setPersona(Personas persona) { this.persona = persona; }

    public Viviendas getVivienda() { return vivienda; }
    public void setVivienda(Viviendas vivienda) { this.vivienda = vivienda; }

    public String getFecha_posesion() { return fecha_posesion; }
    public void setFecha_posesion(String fecha_posesion) { this.fecha_posesion = fecha_posesion; }
}
