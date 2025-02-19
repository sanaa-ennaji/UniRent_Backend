package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Property;

import java.util.List;
@Mapper(componentModel = "spring" )
public interface PropertyMapper {
    @Mapping(source = "user.id", target = "landlordId")
    PropertyResponseDTO toResponseDTO(Property entity) ;
    
    Property toEntity(PropertyRequestDTO requestDTO);
    List<PropertyResponseDTO> toResponseDTOList (List<Property> entities);
    List<Property> toEntityList(List<PropertyRequestDTO> requestDTOs);
    void updateEntityFromRequest(PropertyRequestDTO propertyRequestDTO, @MappingTarget Property property);
}
