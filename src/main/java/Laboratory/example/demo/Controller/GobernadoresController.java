package Laboratory.example.demo.Controller;

import Laboratory.example.demo.DTO.GobernadorDTO;
import Laboratory.example.demo.model.Gobernadores;
import Laboratory.example.demo.model.Municipios;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.service.GobernadoresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gobernadores")
@CrossOrigin(origins = "*")
@Tag(name = "Gobernadores", description = "Controlador para la gestión de gobernadores")
public class GobernadoresController {

    @Autowired
    private GobernadoresService gobernadoresService;

    @Operation(summary = "Crear Gobernador",
            description = "Crea un nuevo gobernador y lo guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Gobernador creado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gobernadores.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<Gobernadores> createGobernador(@Valid @RequestBody Gobernadores gobernador) {
        Gobernadores createdGobernador = gobernadoresService.saveGobernador(gobernador);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGobernador);
    }

    @Operation(summary = "Obtener Gobernador por Persona",
            description = "Recupera el gobernador asociado a una persona específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gobernador encontrado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gobernadores.class))),
                    @ApiResponse(responseCode = "404", description = "Gobernador no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<Gobernadores> getGobernadorByPersona(@PathVariable Personas idPersona) {
        Gobernadores gobernador = gobernadoresService.getGobernadorByPersona(idPersona);
        return gobernador != null ? ResponseEntity.ok(gobernador) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener Gobernadores por Municipio",
            description = "Recupera una lista de gobernadores asociados a un municipio específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de gobernadores obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gobernadores.class))),
                    @ApiResponse(responseCode = "404", description = "Municipio no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/municipio/{idMunicipio}")
    public ResponseEntity<List<Gobernadores>> getGobernadoresByMunicipio(@PathVariable Municipios idMunicipio) {
        List<Gobernadores> gobernadores = gobernadoresService.getGobernadoresByMunicipio(idMunicipio);
        return ResponseEntity.ok(gobernadores);
    }

    @Operation(summary = "Actualizar Gobernador",
            description = "Actualiza los datos de un gobernador específico identificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gobernador actualizado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gobernadores.class))),
                    @ApiResponse(responseCode = "404", description = "Gobernador no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/{id}")
    public ResponseEntity<Gobernadores> updateGobernadores(@PathVariable Long id, @RequestBody Gobernadores updatedGobernadores) {
        Gobernadores result = gobernadoresService.updateGobernadores(id, updatedGobernadores);
        if (result == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el gobernador
        }
        return ResponseEntity.ok(result); // Retorna 200 con el gobernador actualizado
    }

    @Operation(summary = "Eliminar Gobernador",
            description = "Elimina un gobernador específico identificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gobernador eliminado con éxito."),
                    @ApiResponse(responseCode = "404", description = "Gobernador no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGobernadores(@PathVariable Long id) {
        gobernadoresService.deleteGobernadores(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtener todos los Gobernadores",
            description = "Recupera una lista completa de todos los gobernadores registrados en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de gobernadores obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GobernadorDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<GobernadorDTO>> getAllGobernadores() {
        List<Gobernadores> gobernadores = gobernadoresService.getAllGobernadores();
        List<GobernadorDTO> gobernadorDTOs = gobernadores.stream()
                .map(GobernadorDTO::new)
                .toList();
        return ResponseEntity.ok(gobernadorDTOs);
    }
}

