package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring" )
public interface UserMapper {
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.roleName", target = "roleName")
    UserResponseDTO toDto(AppUser appUser);

    @Mapping(source = "roleId", target = "role.id")
    AppUser toEntity(UserRequestDTO dto);
}
