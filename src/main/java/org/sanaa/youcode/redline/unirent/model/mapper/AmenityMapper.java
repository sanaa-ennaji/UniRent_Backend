package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Amenity;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface AmenityMapper {

    AmenityResponseDTO toResponseDTO(Amenity entity) ;
    Amenity toEntity(AmenityRequestDTO requestDTO);
    List<AmenityResponseDTO> toResponseDTOList (List<Amenity> entities);
    List<Amenity> toEntityList(List<AmenityRequestDTO> requestDTOs);
    void updateEntityFromRequest(AmenityRequestDTO amenityRequestDTO,@MappingTarget Amenity amenity);

}
