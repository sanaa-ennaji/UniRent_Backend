package org.sanaa.youcode.redline.unirent.controller;


import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityPropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityPropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.service.AmenityPropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/amenity-properties")
public class  AmenityPropertyController {
    private final AmenityPropertyService amenityPropertyService;

        public AmenityPropertyController(AmenityPropertyService amenityPropertyService) {
            this.amenityPropertyService = amenityPropertyService;
        }


    @GetMapping("/{id}")
    public ResponseEntity<AmenityPropertyResponseDTO> getAmenityPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(amenityPropertyService.getAmenityPropertyById(id));
    }

    @GetMapping
    public ResponseEntity<List<AmenityPropertyResponseDTO>> getAllAmenityProperties() {
        return ResponseEntity.ok(amenityPropertyService.getAllAmenityProperties());
    }

    @PostMapping
    public ResponseEntity<AmenityPropertyResponseDTO> createAmenityProperty(@RequestBody AmenityPropertyRequestDTO requestDTO) {
        return ResponseEntity.ok(amenityPropertyService.createAmenityProperty(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityPropertyResponseDTO> updateAmenityProperty(
        @PathVariable Long id, @RequestBody AmenityPropertyRequestDTO requestDTO) {
        return ResponseEntity.ok(amenityPropertyService.updateAmenityProperty(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmenityProperty(@PathVariable Long id) {
        amenityPropertyService.deleteAmenityProperty(id);
        return ResponseEntity.noContent().build();
    }
}

