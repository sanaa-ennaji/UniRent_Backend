package org.sanaa.youcode.redline.unirent.model.mapper;

import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.springframework.web.bind.annotation.Mapping;

public interface UserMapper {

    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.roleName", target = "roleName")
    AppUserResponseDTO toDto(AppUser appUser);

    @Mapping(source = "roleId", target = "role.id")
    AppUser toEntity(AppUserRequestDTO dto);
}
