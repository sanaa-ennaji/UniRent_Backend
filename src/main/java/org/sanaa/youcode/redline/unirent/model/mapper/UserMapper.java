package org.sanaa.youcode.redline.unirent.model.mapper;

import org.sanaa.youcode.redline.unirent.model.dto.Request.RoleRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.RoleResponseDTO;

import javax.management.relation.Role;

public interface UserMapper {

    RoleResponseDTO toDto(Role role);

    Role toEntity(RoleRequestDTO dto);
}
