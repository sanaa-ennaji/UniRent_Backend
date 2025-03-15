package org.sanaa.youcode.redline.unirent.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityResponseDTO;
import org.sanaa.youcode.redline.unirent.service.ServiceI.AmenityServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/amenity")
public class AmenityController {

    private final AmenityServiceI amenityServiceI;

    @PostMapping
    public ResponseEntity<AmenityResponseDTO> create(@Valid @RequestBody AmenityRequestDTO RequestDTO) {
        AmenityResponseDTO amenityResponse = amenityServiceI.create(RequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AmenityRequestDTO RequestDTO) {
        AmenityResponseDTO updatedAmenity = amenityServiceI.update(id, RequestDTO);
        return ResponseEntity.ok(updatedAmenity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityResponseDTO> findById(@PathVariable Long id) {
        return amenityServiceI.getById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Amenity not found"));
    }

    @GetMapping
    public ResponseEntity<List<AmenityResponseDTO>> findAll() {
        List<AmenityResponseDTO> properties = amenityServiceI.getAll();
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Long id) {
        amenityServiceI.delete(id);
        return ResponseEntity.ok(" Amenity was deleted");
    }
}
