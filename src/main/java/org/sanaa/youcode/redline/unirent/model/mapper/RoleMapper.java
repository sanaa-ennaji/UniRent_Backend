package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.RoleRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.RoleResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppRole;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface RoleMapper {
    RoleResponseDTO toResponseDTO(AppRole entity) ;
    AppRole toEntity(RoleRequestDTO requestDTO);
    List<RoleResponseDTO> toResponseDTOList (List<AppRole> entities);
    List<AppRole> toEntityList(List<RoleRequestDTO> requestDTOs);
    void updateEntityFromRequest(RoleRequestDTO roleRequestDTO,@MappingTarget AppRole role);

}
