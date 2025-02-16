package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityPropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityPropertyResponseDTO;

import java.util.List;

public interface AmenityPropertyServiceI {
    AmenityPropertyResponseDTO getAmenityPropertyById(Long id);
    List<AmenityPropertyResponseDTO> getAllAmenityProperties();
    AmenityPropertyResponseDTO createAmenityProperty(AmenityPropertyRequestDTO requestDTO);
    AmenityPropertyResponseDTO updateAmenityProperty(Long id, AmenityPropertyRequestDTO requestDTO);
    void deleteAmenityProperty(Long id);
}
