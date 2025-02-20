package org.sanaa.youcode.redline.unirent.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.sanaa.youcode.redline.unirent.service.ServiceI.UniversityServiceI;
import org.sanaa.youcode.redline.unirent.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityServiceI universityServiceI ;
    private final UniversityService universityService;

    @PostMapping
    public ResponseEntity<UniversityResponseDTO> create(@Valid @RequestBody UniversityRequestDTO answerRequestDTO) {
        UniversityResponseDTO answerResponse = universityServiceI.create(answerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(answerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UniversityRequestDTO answerRequestDTO) {
        UniversityResponseDTO updatedAnswer = universityService.update(id, answerRequestDTO);
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
