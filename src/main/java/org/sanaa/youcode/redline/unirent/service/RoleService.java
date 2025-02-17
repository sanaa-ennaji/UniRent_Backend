package org.sanaa.youcode.redline.unirent.service;

import org.sanaa.youcode.redline.unirent.model.dto.Request.RoleRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.RoleResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppRole;
import org.sanaa.youcode.redline.unirent.model.mapper.RoleMapper;
import org.sanaa.youcode.redline.unirent.repository.RoleRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.RoleServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceI {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        AppRole role = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.toResponseDTO(role);

    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return roleMapper.toResponseDTOList(roleRepository.findAll());
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO requestDTO) {
        AppRole role = roleMapper.toEntity(requestDTO);
        return roleMapper.toResponseDTO(roleRepository.save(role));

    }

    @Override
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO requestDTO) {
        AppRole role = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        roleMapper.updateEntityFromRequest(requestDTO, role);
        return roleMapper.toResponseDTO(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
