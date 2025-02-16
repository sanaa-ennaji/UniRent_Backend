package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;

import java.util.List;

public interface PropertyServiceI {
    PropertyResponseDTO getPropertyById(Long id);
    List<PropertyResponseDTO> getAllProperties();
    PropertyResponseDTO createProperty(PropertyRequestDTO requestDTO);
    PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO requestDTO);
    void deleteProperty(Long id);
}
