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

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

private PropertyServiceI propertyServiceI;
    @PostMapping
    public ResponseEntity<PropertyResponseDTO> create(@Valid @RequestBody PropertyRequestDTO RequestDTO) {
        PropertyResponseDTO propertyResponse = propertyServiceI.createProperty(RequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PropertyRequestDTO RequestDTO) {
        PropertyResponseDTO updatedAnswer = propertyServiceI.update(id, RequestDTO);
        return ResponseEntity.ok(updatedAnswer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> findById(@PathVariable Long id) {
        return universityService.getById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found"));
    }

    @GetMapping
    public ResponseEntity<List<UniversityResponseDTO>> findAll() {
        List<UniversityResponseDTO> answers = universityService.getAll();
        return ResponseEntity.ok(answers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        universityService.delete(id);
        return ResponseEntity.ok("Answer was deleted");
    }
}
}
