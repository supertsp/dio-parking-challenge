package br.com.tiagopedroso.dioparkingchallenge.controller;

import br.com.tiagopedroso.dioparkingchallenge.controller.dto.ParkingCreateDto;
import br.com.tiagopedroso.dioparkingchallenge.controller.dto.ParkingDto;
import br.com.tiagopedroso.dioparkingchallenge.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.tiagopedroso.dioparkingchallenge.config.ApiUrl.BASE_URI;
import static br.com.tiagopedroso.dioparkingchallenge.controller.mapper.ParkingMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping(BASE_URI + "/parking")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Parking", description = "Operations to manipulate the parking of each vehicle")
public class ParkingController {

    final ParkingService service;

    @Operation(summary = "Find all Parking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the all Parking",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ParkingDto.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ParkingDto>> findAll() {
        final var modelList = service.findAll();

        return ResponseEntity.ok(
                toDtoList(modelList)
        );
    }

    @Operation(summary = "Finds a Parking by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Parking",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ParkingDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Parking not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findById(@PathVariable String id) {
        final var model = service.findById(id);

        return ResponseEntity.ok(
                toDto(model)
        );
    }

    @PostMapping
    public ResponseEntity<ParkingDto> create(@RequestBody ParkingCreateDto dto) {
        final var model = service.create(toModel(dto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        toDto(model)
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDto> update(@PathVariable String id, @RequestBody ParkingCreateDto parkingCreteDTO) {
        final var model = service.update(id, toModel(parkingCreteDTO));

        return ResponseEntity.ok(toDto(model));
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ParkingDto> checkOut(@PathVariable String id) {
        final var model = service.checkOut(id);

        return ResponseEntity.ok(
                toDto(model)
        );
    }

}
