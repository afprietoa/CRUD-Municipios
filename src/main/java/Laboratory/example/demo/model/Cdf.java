package Laboratory.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "cdf")
public class Cdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "FK_cdf_persona", value = ConstraintMode.CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (id_persona) REFERENCES personas(id) ON DELETE CASCADE"))
    private Personas persona;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vivienda", foreignKey = @ForeignKey(name = "FK_cdf_vivienda", value = ConstraintMode.CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (id_vivienda) REFERENCES viviendas(id) ON DELETE CASCADE"))
    private Viviendas vivienda;

    @NotBlank(message = "The registration date cannot be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The registration date must follow the format YYYY-MM-DD")
    private String fecha_registro;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Personas getPersona() { return persona; }
    public void setPersona(Personas persona) { this.persona = persona; }

    public Viviendas getVivienda() { return vivienda; }
    public void setVivienda(Viviendas vivienda) { this.vivienda = vivienda; }

    public String getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(String fecha_registro) { this.fecha_registro = fecha_registro; }
}
