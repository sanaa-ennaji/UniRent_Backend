package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityPropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityPropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AmenityProperty;

import java.util.List;
@Mapper(componentModel = "spring" )
public interface AmenityPropertyMapper {
    @Mapping(source = "amenity.id", target = "amenityId")
    @Mapping(source = "property.id", target = "propertyId")
    AmenityPropertyResponseDTO toResponseDTO(AmenityProperty entity) ;
    @Mapping(source = "propertyId", target = "property.id")
    @Mapping(source = "amenityId", target = "amenity.id")
    AmenityProperty toEntity(AmenityPropertyRequestDTO requestDTO);
    List<AmenityPropertyResponseDTO> toResponseDTOList (List<AmenityProperty> entities);
    List<AmenityProperty> toEntityList(List<AmenityPropertyRequestDTO> requestDTOs);
    void updateEntityFromRequest(AmenityPropertyRequestDTO amenityRequestDTO,@MappingTarget AmenityProperty amenityProperty);
}
