package Laboratory.example.demo.Controller;


import Laboratory.example.demo.DTO.MunicipioDTO;
import Laboratory.example.demo.model.Municipios;
import Laboratory.example.demo.service.MunicipiosService;
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
@RequestMapping("/api/municipios")
@CrossOrigin(origins = "*")
@Tag(name = "Municipios", description = "Controlador para la gestión de municipios")
public class MunicipiosController {

    @Autowired
    private MunicipiosService municipiosService;

    @Operation(summary = "Crear Municipio",
            description = "Crea un nuevo municipio y lo guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Municipio creado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Municipios.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<Municipios> createMunicipio(@Valid @RequestBody Municipios municipio) {
        Municipios createdMunicipio = municipiosService.saveMunicipio(municipio);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMunicipio);
    }

    @Operation(summary = "Obtener Municipio por ID",
            description = "Recupera los detalles de un municipio específico por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Municipio encontrado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MunicipioDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Municipio no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDTO> getMunicipioById(@PathVariable Long id) {
        Municipios municipio = municipiosService.getMunicipioById(id);
        return municipio != null ? ResponseEntity.ok(new MunicipioDTO(municipio)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener Municipio por Nombre",
            description = "Recupera los detalles de un municipio específico filtrado por nombre.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Municipio encontrado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Municipios.class))),
                    @ApiResponse(responseCode = "404", description = "Municipio no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/nombre")
    public ResponseEntity<Municipios> getMunicipioByNombre(@RequestParam String nombre) {
        Municipios municipio = municipiosService.getMunicipioByNombre(nombre);
        return municipio != null ? ResponseEntity.ok(municipio) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener Todos los Municipios Ordenados",
            description = "Recupera una lista de todos los municipios ordenados alfabéticamente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de municipios obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Municipios.class)))
            })
    @GetMapping("/ordenados")
    public ResponseEntity<List<Municipios>> getAllMunicipiosOrdenados() {
        List<Municipios> municipios = municipiosService.getAllMunicipiosOrdenados();
        return ResponseEntity.ok(municipios);
    }

    @Operation(summary = "Eliminar Municipio",
            description = "Elimina un municipio específico identificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Municipio eliminado con éxito."),
                    @ApiResponse(responseCode = "404", description = "Municipio no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosesiones(@PathVariable Long id) {
        municipiosService.deleteMunicipio(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Actualizar Municipio",
            description = "Actualiza los datos de un municipio específico identificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Municipio actualizado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Municipios.class))),
                    @ApiResponse(responseCode = "404", description = "Municipio no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/{id}")
    public ResponseEntity<Municipios> updateMunicipio(@PathVariable Long id, @RequestBody Municipios updatedMunicipio) {
        Municipios result = municipiosService.updateMunicipio(id, updatedMunicipio);
        if (result == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el municipio
        }
        return ResponseEntity.ok(result); // Retorna 200 con el municipio actualizado
    }

    @Operation(summary = "Obtener todos los Municipios",
            description = "Recupera una lista completa de todos los municipios registrados en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de municipios obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MunicipioDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> getAllMunicipios() {
        List<Municipios> municipios = municipiosService.getAllMunicipios();
        List<MunicipioDTO> municipiosDTO = municipios.stream()
                .map(MunicipioDTO::new)
                .toList();
        return ResponseEntity.ok(municipiosDTO);
    }
}

