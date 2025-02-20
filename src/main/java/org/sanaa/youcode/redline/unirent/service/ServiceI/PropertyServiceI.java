package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyServiceI {
    Optional<PropertyResponseDTO> getById(Long id);
    List<PropertyResponseDTO> getAll();
    PropertyResponseDTO create(PropertyRequestDTO requestDTO);
    PropertyResponseDTO update(Long id, PropertyRequestDTO requestDTO);
    void delete(Long id);
}
