package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.ImageRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ImageResponseDTO;

import java.util.List;

public interface ImageServiceI {
    ImageResponseDTO getImageById(Long id);
    List<ImageResponseDTO> getAllImages();
    ImageResponseDTO uploadImage(ImageRequestDTO requestDTO);
    ImageResponseDTO updateImage(Long id, ImageRequestDTO requestDTO);
    void deleteImage(Long id);
}
