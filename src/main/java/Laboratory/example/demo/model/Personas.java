package Laboratory.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "personas")
public class Personas {

    @Id
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    @Max(value = 9999999999L, message = "ID must be less than or equal to 9999999999")
    private Long id;

    @NotBlank(message = "Document type cannot be blank")
    @Pattern(regexp = "^(CC|TI|CE|PASSPORT)$", message = "Invalid document type. Allowed values: CC, TI, CE, PASSPORT")
    private String tipo_doc;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String nombre;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private Date fechaNac;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(M|F)$", message = "Invalid gender. Allowed values: M, F")
    private String sexo;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_vivienda_actual", nullable = true)
    @JsonBackReference
    private Viviendas viviendaActual;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo_doc() { return tipo_doc; }
    public void setTipo_doc(String tipo_doc) { this.tipo_doc = tipo_doc; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFechaNac() { return fechaNac; }
    public void setFechaNac(Date fecha_nac) { this.fechaNac = fecha_nac; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Viviendas getViviendaActual() { return viviendaActual; }
    public void setViviendaActual(Viviendas viviendaActual) { this.viviendaActual = viviendaActual; }
}