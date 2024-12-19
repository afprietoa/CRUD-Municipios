package Laboratory.example.demo.Controller;


import Laboratory.example.demo.DTO.CdfDTO;
import Laboratory.example.demo.model.Cdf;
import Laboratory.example.demo.model.Personas;
import Laboratory.example.demo.service.CdfService;
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
@RequestMapping("/api/cdf")
@CrossOrigin(origins = "*")
@Tag(name = "CDF", description = "Controlador para la gestión de CDF (Centros de Formación)")
public class CdfController {

    @Autowired
    private CdfService cdfService;

    @Operation(summary = "Crear CDF",
            description = "Crea un nuevo Centro de Formación (CDF) y lo guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CDF creado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cdf.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<Cdf> createCdf(@Valid @RequestBody Cdf cdf) {
        Cdf createdCdf = cdfService.saveCdf(cdf);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCdf);
    }

    @Operation(summary = "Obtener CDF por Persona",
            description = "Recupera una lista de Centros de Formación (CDF) asociados a una persona específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de CDF obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cdf.class))),
                    @ApiResponse(responseCode = "404", description = "Persona no encontrada.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<List<Cdf>> getCdfByPersona(@PathVariable Long idPersona) {
        List<Cdf> cdfs = cdfService.getCdfByPersona(idPersona);
        return ResponseEntity.ok(cdfs);
    }

    @Operation(summary = "Contar CDF",
            description = "Obtiene el número total de Centros de Formación (CDF) registrados en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cantidad de CDF contada con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
            })
    @GetMapping("/count")
    public ResponseEntity<Long> countCdf() {
        long count = cdfService.countCdf();
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "Obtener todos los CDF",
            description = "Recupera una lista completa de todos los Centros de Formación (CDF) registrados en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de CDF obtenida con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CdfDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<CdfDTO>> getAllCdf() {
        List<Cdf> cdfList = cdfService.getAllCdf();
        List<CdfDTO> cdfDTOs = cdfList.stream()
                .map(CdfDTO::new)
                .toList();
        return ResponseEntity.ok(cdfDTOs);
    }

    @Operation(summary = "Eliminar CDF",
            description = "Elimina un Centro de Formación (CDF) específico identificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "CDF eliminado con éxito."),
                    @ApiResponse(responseCode = "404", description = "CDF no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCdf(@PathVariable Long id) {
        boolean isDeleted = cdfService.deleteCdf(id);
        if (isDeleted) {
            return ResponseEntity.ok().build(); // Retorna 200 si se eliminó correctamente
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encontró el registro
    }

    @Operation(summary = "Actualizar CDF",
            description = "Actualiza los datos de un Centro de Formación (CDF) específico identificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "CDF actualizado con éxito.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cdf.class))),
                    @ApiResponse(responseCode = "404", description = "CDF no encontrado.",
                            content = @Content(schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PutMapping("/{id}")
    public ResponseEntity<Cdf> updateCdf(@PathVariable Long id, @Valid @RequestBody Cdf updatedCdf) {
        Cdf result = cdfService.updateCdf(id, updatedCdf);
        if (result != null) {
            return ResponseEntity.ok(result); // Retorna 200 con el CDF actualizado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el registro
    }

}
