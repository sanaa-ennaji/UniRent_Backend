package org.sanaa.youcode.redline.unirent.controller;

import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.TransactionRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.TransactionResponseDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.University;
import org.sanaa.youcode.redline.unirent.service.ServiceI.UniversityServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityServiceI universityServiceI ;
    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(universityServiceI.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO requestDTO) {
        return ResponseEntity.ok(transactionService.createTransaction(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(
        @PathVariable Long id, @RequestBody TransactionRequestDTO requestDTO) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
