package Laboratory.example.demo.Controller;


import Laboratory.example.demo.DTO.ViviendaDTO;
import Laboratory.example.demo.model.Municipios;
import Laboratory.example.demo.model.Viviendas;
import Laboratory.example.demo.service.ViviendasService;
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
@RequestMapping("/api/viviendas")
@CrossOrigin(origins = "*")
@Tag(name = "Viviendas", description = "Controlador para la gestión de viviendas")
public class ViviendasController {

    @Autowired
    private ViviendasService viviendasService;

    @Operation(summary = "Crear Vivienda",
            description = "Crea una nueva vivienda y la guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Vivienda creada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viviendas.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<Viviendas> createVivienda(@Valid @RequestBody Viviendas vivienda) {
        Viviendas createdVivienda = viviendasService.saveVivienda(vivienda);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVivienda);
    }

    @Operation(summary = "Obtener Vivienda por ID",
            description = "Recupera una vivienda específica por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vivienda encontrada.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViviendaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Vivienda no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/{id}")
    public ResponseEntity<ViviendaDTO> getViviendaById(@PathVariable Long id) {
        Viviendas vivienda = viviendasService.getViviendaById(id);
        return vivienda != null ? ResponseEntity.ok(new ViviendaDTO(vivienda)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Vivienda",
            description = "Elimina una vivienda por su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Vivienda eliminada con éxito."),
                    @ApiResponse(responseCode = "404", description = "Vivienda no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVivienda(@PathVariable Long id) {
        viviendasService.deleteVivienda(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener Viviendas por Capacidad",
            description = "Obtiene una lista de viviendas que tienen una capacidad específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de viviendas obtenida.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viviendas.class)))
            })
    @GetMapping("/capacidad")
    public ResponseEntity<List<Viviendas>> getViviendasByCapacidad(@RequestParam int capacidad) {
        List<Viviendas> viviendas = viviendasService.getViviendasByCapacidad(capacidad);
        return ResponseEntity.ok(viviendas);
    }

    @Operation(summary = "Obtener Viviendas por Municipio",
            description = "Recupera una lista de viviendas de un municipio específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de viviendas obtenida.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viviendas.class)))
            })
    @GetMapping("/municipio/{idMunicipio}")
    public ResponseEntity<List<Viviendas>> getViviendasByMunicipio(@PathVariable Municipios idMunicipio) {
        List<Viviendas> viviendas = viviendasService.getViviendasByMunicipio(idMunicipio);
        return ResponseEntity.ok(viviendas);
    }

    @Operation(summary = "Contar Viviendas con Niveles Mayores",
            description = "Cuenta la cantidad de viviendas que tienen un número de niveles mayor al proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Número de viviendas contado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
            })
    @GetMapping("/niveles-mayor")
    public ResponseEntity<Long> countViviendasConNivelesMayor(@RequestParam int niveles) {
        long count = viviendasService.countViviendasConNivelesMayor(niveles);
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "Obtener todas las Viviendas",
            description = "Recupera una lista completa de todas las viviendas registradas en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de viviendas obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViviendaDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<ViviendaDTO>> getAllViviendas() {
        List<Viviendas> viviendas = viviendasService.getAllViviendas();
        List<ViviendaDTO> viviendasDTO = viviendas.stream()
                .map(ViviendaDTO::new)
                .toList();
        return ResponseEntity.ok(viviendasDTO);
    }

    @Operation(summary = "Actualizar Vivienda",
            description = "Actualiza los datos de una vivienda específica identificada por su ID. Además, valida y asocia un Municipio existente a la vivienda.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vivienda actualizada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Viviendas.class))),
                    @ApiResponse(responseCode = "404", description = "Vivienda o Municipio no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/{id}")
    public ResponseEntity<Viviendas> updateVivienda(@PathVariable Long id, @Valid @RequestBody Viviendas updatedVivienda) {
        Viviendas result = viviendasService.updateVivienda(id, updatedVivienda);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

}
