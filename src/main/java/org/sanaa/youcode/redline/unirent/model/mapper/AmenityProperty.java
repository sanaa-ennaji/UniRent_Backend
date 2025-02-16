package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityPropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityPropertyResponseDTO;

import java.util.List;
@Mapper(componentModel = "spring" )
public interface AmenityProperty {
    AmenityPropertyResponseDTO toResponseDTO(AmenityProperty entity) ;
    AmenityProperty toEntity(AmenityPropertyRequestDTO requestDTO);
    List<AmenityPropertyResponseDTO> toResponseDTOList (List<AmenityProperty> entities);
    List<AmenityProperty> toEntityList(List<AmenityPropertyRequestDTO> requestDTOs);
    void updateEntityFromRequest(AmenityPropertyRequestDTO amenityRequestDTO,@MappingTarget AmenityProperty amenityProperty);
}
