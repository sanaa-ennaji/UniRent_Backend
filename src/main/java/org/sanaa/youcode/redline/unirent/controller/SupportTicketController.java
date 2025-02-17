package org.sanaa.youcode.redline.unirent.controller;

import org.sanaa.youcode.redline.unirent.model.dto.Request.SupportTicketRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.SupportTicketResponseDTO;
import org.sanaa.youcode.redline.unirent.service.SupportTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support-tickets")
public class SupportTicketController {
    private final SupportTicketService supportTicketService;

    public SupportTicketController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportTicketResponseDTO> getSupportTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(supportTicketService.getTicketById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupportTicketResponseDTO>> getAllSupportTickets() {
        return ResponseEntity.ok(supportTicketService.getAllTickets());
    }

    @PostMapping
    public ResponseEntity<SupportTicketResponseDTO> createSupportTicket(@RequestBody SupportTicketRequestDTO requestDTO) {
        return ResponseEntity.ok(supportTicketService.createTicket(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportTicketResponseDTO> updateSupportTicket(
        @PathVariable Long id, @RequestBody SupportTicketRequestDTO requestDTO) {
        return ResponseEntity.ok(supportTicketService.updateTicket(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportTicket(@PathVariable Long id) {
        supportTicketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}

