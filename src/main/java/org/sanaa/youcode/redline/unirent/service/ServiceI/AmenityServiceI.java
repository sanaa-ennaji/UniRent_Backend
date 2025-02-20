package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AmenityServiceI {
   Optional<AmenityResponseDTO>  getById(Long id);
    List<AmenityResponseDTO> getAll();
    AmenityResponseDTO create(AmenityRequestDTO requestDTO);
    AmenityResponseDTO update(Long id, AmenityRequestDTO requestDTO);
    void delete(Long id);
}
