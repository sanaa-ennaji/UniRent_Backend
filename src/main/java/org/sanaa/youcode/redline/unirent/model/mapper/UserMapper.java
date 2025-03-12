package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface UserMapper {
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.roleName", target = "roleName")
    UserResponseDTO toResponseDto(AppUser appUser);

    @Mapping(source = "roleId", target = "role.id")
    AppUser toEntity(UserRequestDTO dto);

    List<UserResponseDTO> toResponseDTOList(List<AppUser> entities);

    List<AppUser> toEntityList(List<UserRequestDTO> requestDTOs);

    void updateEntityFromRequest(UserRequestDTO amenityRequestDTO, @MappingTarget AppUser appUser);
}
