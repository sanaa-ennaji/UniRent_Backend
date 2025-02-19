package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.University;


import java.util.List;

@Mapper(componentModel = "spring" )
public interface UniversityMapper {
    UniversityResponseDTO toResponseDTO(University entity) ;
    University toEntity(UniversityRequestDTO requestDTO);
    List<UniversityResponseDTO> toResponseDTOList (List<University> entities);
    List<University> toEntityList(List<UniversityRequestDTO> requestDTOs);
    void updateEntityFromRequest(UniversityRequestDTO amenityRequestDTO,@MappingTarget University university);
}
