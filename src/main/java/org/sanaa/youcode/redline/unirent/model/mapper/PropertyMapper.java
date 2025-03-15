package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.sanaa.youcode.redline.unirent.model.entity.University;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" )
public interface PropertyMapper {
    @Mapping(target = "landlordId", source = "landlord.id")
    @Mapping(target = "universityIds", source = "universities", qualifiedByName = "mapUniversitiesToIds")
    PropertyResponseDTO toResponseDTO(Property entity);


    @Named("mapUniversitiesToIds")
    default List<Long> mapUniversitiesToIds(List<University> universities) {
        if (universities == null) {
            return Collections.emptyList();
        }
        return universities.stream()
            .map(University::getId)
            .collect(Collectors.toList());
    }
    @Mapping(target = "images", ignore = true)
    Property toEntity(PropertyRequestDTO requestDTO);
    List<PropertyResponseDTO> toResponseDTOList (List<Property> entities);
    List<Property> toEntityList(List<PropertyRequestDTO> requestDTOs);
    void updateEntityFromRequest(PropertyRequestDTO propertyRequestDTO, @MappingTarget Property property);
}
