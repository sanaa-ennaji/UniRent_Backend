package org.sanaa.youcode.redline.unirent.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.service.ServiceI.PropertyServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

private final PropertyServiceI propertyServiceI;
    @PostMapping
    public ResponseEntity<PropertyResponseDTO> create(@Valid @RequestBody PropertyRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
        PropertyResponseDTO propertyResponse = propertyServiceI.create(requestDTO);
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

    @GetMapping("/search")
    public ResponseEntity<List<PropertyResponseDTO>> searchProperties(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Double price,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
        List<PropertyResponseDTO> properties = propertyServiceI.searchProperties(title, price, startDate);
        return ResponseEntity.ok(properties);
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
