package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.RoleRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.RoleResponseDTO;

import java.util.List;

public interface RoleServiceI {
    RoleResponseDTO getRoleById(Long id);
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO createRole(RoleRequestDTO requestDTO);
    RoleResponseDTO updateRole(Long id, RoleRequestDTO requestDTO);
    void deleteRole(Long id);
}
