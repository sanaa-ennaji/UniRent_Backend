package org.sanaa.youcode.redline.unirent.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.service.ServiceI.PropertyServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

private PropertyServiceI propertyServiceI;
    @PostMapping
    public ResponseEntity<PropertyResponseDTO> create(@Valid @RequestBody PropertyRequestDTO RequestDTO) {
        PropertyResponseDTO propertyResponse = propertyServiceI.create(RequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PropertyRequestDTO RequestDTO) {
        PropertyResponseDTO updatedProperty = propertyServiceI.update(id, RequestDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> findById(@PathVariable Long id) {
        return propertyServiceI.getById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "property not found"));
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> findAll() {
        List<PropertyResponseDTO> properties = propertyServiceI.getAll();
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        propertyServiceI.delete(id);
        return ResponseEntity.ok(" property was deleted");
    }
}
