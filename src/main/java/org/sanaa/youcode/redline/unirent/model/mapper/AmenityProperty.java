package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface AmenityProperty {
    ResponseDTO toResponseDTO(Amenity entity) ;
    Amenity toEntity(AmenityRequestDTO requestDTO);
    List<AmenityResponseDTO> toResponseDTOList (List<Amenity> entities);
    List<Amenity> toEntityList(List<AmenityRequestDTO> requestDTOs);
    void updateEntityFromRequest(AmenityRequestDTO amenityRequestDTO,@MappingTarget Amenity amenity);
}
