package org.sanaa.youcode.redline.unirent.controller;

import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.ImageRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ImageResponseDTO;
import org.sanaa.youcode.redline.unirent.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponseDTO> getImageById(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImageById(id));
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDTO>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ImageResponseDTO> updateImage(
        @PathVariable Long id, @RequestBody ImageRequestDTO requestDTO) {
        return ResponseEntity.ok(imageService.updateImage(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}

