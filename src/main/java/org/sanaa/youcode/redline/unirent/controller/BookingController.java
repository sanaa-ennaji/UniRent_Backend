package org.sanaa.youcode.redline.unirent.controller;

import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.BookingRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.BookingResponseDTO;
import org.sanaa.youcode.redline.unirent.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@Validated
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;


    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO requestDTO) {
        return ResponseEntity.ok(bookingService.createBooking(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
        @PathVariable Long id, @RequestBody BookingRequestDTO requestDTO) {
        return ResponseEntity.ok(bookingService.updateBooking(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
