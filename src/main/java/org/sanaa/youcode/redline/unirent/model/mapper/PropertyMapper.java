package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Amenity;
import org.sanaa.youcode.redline.unirent.model.entity.Image;
import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.sanaa.youcode.redline.unirent.model.entity.University;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    @Mapping(target = "landlordId", source = "landlord.id")
    @Mapping(target = "landlordName", source = "landlord.name")
    @Mapping(target = "universityIds", source = "universities", qualifiedByName = "mapUniversitiesToIds")
    @Mapping(target = "imageUrls", source = "images", qualifiedByName = "mapImagesToUrls")
    @Mapping(target = "amenities", source = "amenities", qualifiedByName = "mapAmenitiesToDTOs")
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


    @Named("mapImagesToUrls")
    default List<String> mapImagesToUrls(List<Image> images) {
        if (images == null) {
            return Collections.emptyList();
        }
        return images.stream()
            .map(Image::getImageUrl)
            .collect(Collectors.toList());
    }

    @Named("mapAmenitiesToDTOs")
    default List<AmenityResponseDTO> mapAmenitiesToDTOs(List<Amenity> amenities) {
        if (amenities == null) {
            return Collections.emptyList();
        }
        return amenities.stream()
            .map(this::toAmenityResponseDTO)
            .collect(Collectors.toList());
    }

    default AmenityResponseDTO toAmenityResponseDTO(Amenity amenity) {
        AmenityResponseDTO dto = new AmenityResponseDTO();
        dto.setId(amenity.getId());
        dto.setName(amenity.getName());
        return dto;
    }

    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "images", ignore = true)
    Property toEntity(PropertyRequestDTO requestDTO);
    List<PropertyResponseDTO> toResponseDTOList(List<Property> entities);
    List<Property> toEntityList(List<PropertyRequestDTO> requestDTOs);
    void updateEntityFromRequest(PropertyRequestDTO propertyRequestDTO, @MappingTarget Property property);
}
