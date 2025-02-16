package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.sanaa.youcode.redline.unirent.model.dto.Request.RoleRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.RoleResponseDTO;

import javax.management.relation.Role;
@Mapper(componentModel = "spring" )
public interface UserMapper {
    RoleResponseDTO toDto(Role role);
    Role toEntity(RoleRequestDTO dto);
}
