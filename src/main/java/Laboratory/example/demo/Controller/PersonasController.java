package Laboratory.example.demo.Controller;


import Laboratory.example.demo.DTO.PersonaDTO;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.service.PersonasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
@Tag(name = "Personas", description = "Controlador para la gestión de personas")
public class PersonasController {

    @Autowired
    private PersonasService personasService;

    @Operation(summary = "Crear Persona",
            description = "Crea una nueva persona y la guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Persona creada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personas.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<Personas> createPersona(@Valid @RequestBody Personas persona) {
        Personas createdPersona = personasService.savePersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPersona);
    }

    @Operation(summary = "Obtener Persona por ID",
            description = "Recupera los detalles de una persona específica por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Persona encontrada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Persona no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable Long id) {
        Personas persona = personasService.getPersonaById(id);
        return persona != null ? ResponseEntity.ok(new PersonaDTO(persona)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Persona",
            description = "Elimina una persona por su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Persona eliminada con éxito."),
                    @ApiResponse(responseCode = "404", description = "Persona no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personasService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener Personas por Nombre",
            description = "Recupera una lista de personas filtradas por nombre.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de personas obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personas.class)))
            })
    @GetMapping("/nombre")
    public ResponseEntity<List<Personas>> getPersonasByNombre(@RequestParam String nombre) {
        List<Personas> personas = personasService.getPersonasByNombre(nombre);
        return ResponseEntity.ok(personas);
    }

    @Operation(summary = "Obtener Personas por Sexo",
            description = "Recupera una lista de personas filtradas por sexo.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de personas obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personas.class)))
            })
    @GetMapping("/sexo")
    public ResponseEntity<List<Personas>> getPersonasBySexo(@RequestParam String sexo) {
        List<Personas> personas = personasService.getPersonasBySexo(sexo);
        return ResponseEntity.ok(personas);
    }

    @Operation(summary = "Obtener Personas Nacidas Después de Fecha",
            description = "Recupera una lista de personas nacidas después de la fecha especificada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de personas obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personas.class)))
            })
    @GetMapping("/nacidas-despues")
    public ResponseEntity<List<Personas>> getPersonasNacidasDespues(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        List<Personas> personas = personasService.getPersonasNacidasDespues(fecha);
        return ResponseEntity.ok(personas);
    }

    @Operation(summary = "Obtener Personas Sin Vivienda",
            description = "Recupera una lista de personas que no tienen una vivienda asignada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de personas obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personas.class)))
            })
    @GetMapping("/sin-vivienda")
    public ResponseEntity<List<Personas>> getPersonasSinVivienda() {
        List<Personas> personas = personasService.getPersonasSinVivienda();
        return ResponseEntity.ok(personas);
    }

    @Operation(summary = "Actualizar Persona",
            description = "Actualiza los datos de una persona específica identificada por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Persona actualizada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personas.class))),
                    @ApiResponse(responseCode = "404", description = "Persona no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/{id}")
    public ResponseEntity<Personas> updatePersonas(@PathVariable Long id, @RequestBody Personas updatedPersona) {
        Personas result = personasService.updatePersonas(id, updatedPersona);
        if (result == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra la persona
        }
        return ResponseEntity.ok(result); // Retorna 200 con la persona actualizada
    }

    @Operation(summary = "Obtener todas las Personas",
            description = "Recupera una lista completa de todas las personas registradas en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de personas obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonaDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        List<Personas> personas = personasService.getAllPersonas();
        List<PersonaDTO> personasDTO = personas.stream()
                .map(PersonaDTO::new)
                .toList();
        return ResponseEntity.ok(personasDTO);
    }

}
