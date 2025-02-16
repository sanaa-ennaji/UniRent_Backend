package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.RoleRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.RoleResponseDTO;

import javax.management.relation.Role;
import java.util.List;

@Mapper(componentModel = "spring" )
public interface RoleMapper {
    RoleResponseDTO toResponseDTO(Role entity) ;
    Role toEntity(RoleRequestDTO requestDTO);
    List<RoleResponseDTO> toResponseDTOList (List<Role> entities);
    List<Role> toEntityList(List<RoleRequestDTO> requestDTOs);
    void updateEntityFromRequest(RoleRequestDTO amenityRequestDTO,@MappingTarget Role role);

}
