package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityResponseDTO;

import java.util.List;

public interface AmenityServiceI {
    AmenityResponseDTO getAmenityById(Long id);
    List<AmenityResponseDTO> getAllAmenities();
    AmenityResponseDTO createAmenity(AmenityRequestDTO requestDTO);
    AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO requestDTO);
    void deleteAmenity(Long id);
}
