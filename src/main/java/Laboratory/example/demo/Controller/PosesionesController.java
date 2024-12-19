package Laboratory.example.demo.Controller;


import Laboratory.example.demo.DTO.PosesionesDTO;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.model.Posesiones;
import Laboratory.example.demo.model.Viviendas;
import Laboratory.example.demo.service.PosesionesService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posesiones")
@CrossOrigin(origins = "*")
@Tag(name = "Posesiones", description = "Controlador para la gestión de posesiones")
public class PosesionesController {

    @Autowired
    private PosesionesService posesionesService;

    @Operation(summary = "Crear Posesión",
            description = "Crea una nueva posesión y la guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Posesión creada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Posesiones.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<Posesiones> createPosesion(@Valid @RequestBody Posesiones posesion) {
        Posesiones createdPosesion = posesionesService.savePosesion(posesion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPosesion);
    }

    @Operation(summary = "Obtener Posesiones por Persona",
            description = "Recupera una lista de posesiones asociadas a una persona específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de posesiones obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Posesiones.class))),
                    @ApiResponse(responseCode = "404", description = "Persona no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<List<Posesiones>> getPosesionesByPersona(@PathVariable Personas idPersona) {
        List<Posesiones> posesiones = posesionesService.getPosesionesByPersona(idPersona);
        return ResponseEntity.ok(posesiones);
    }

    @Operation(summary = "Obtener Posesiones por Vivienda",
            description = "Recupera una lista de posesiones asociadas a una vivienda específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de posesiones obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Posesiones.class))),
                    @ApiResponse(responseCode = "404", description = "Vivienda no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/vivienda/{idVivienda}")
    public ResponseEntity<List<Posesiones>> getPosesionesByVivienda(@PathVariable Viviendas idVivienda) {
        List<Posesiones> posesiones = posesionesService.getPosesionesByVivienda(idVivienda);
        return ResponseEntity.ok(posesiones);
    }

    @Operation(summary = "Contar Posesiones",
            description = "Obtiene el número total de posesiones registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cantidad de posesiones contada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
            })
    @GetMapping("/count")
    public ResponseEntity<Long> countPosesiones() {
        long count = posesionesService.countPosesiones();
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "Obtener todas las Posesiones",
            description = "Recupera una lista completa de todas las posesiones registradas en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de posesiones obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PosesionesDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<PosesionesDTO>> getAllPosesiones() {
        List<Posesiones> posesiones = posesionesService.getAllPosesiones();
        List<PosesionesDTO> posesionesDTO = posesiones.stream()
                .map(PosesionesDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posesionesDTO);
    }

    @Operation(summary = "Actualizar Posesión",
            description = "Actualiza los datos de una posesión específica identificada por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Posesión actualizada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PosesionesDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Posesión no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/{id}")
    public ResponseEntity<PosesionesDTO> updatePosesiones(
            @PathVariable Long id,
            @RequestBody PosesionesDTO posesionesDTO) {
        Posesiones updatedPosesion = posesionesService.updatePosesiones(id, posesionesDTO);
        if (updatedPosesion != null) {
            return ResponseEntity.ok(new PosesionesDTO(updatedPosesion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar Posesión",
            description = "Elimina una posesión específica identificada por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Posesión eliminada con éxito."),
                    @ApiResponse(responseCode = "404", description = "Posesión no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosesiones(@PathVariable Long id) {
        posesionesService.deletePosesiones(id);
        return ResponseEntity.ok().build();
    }
}
