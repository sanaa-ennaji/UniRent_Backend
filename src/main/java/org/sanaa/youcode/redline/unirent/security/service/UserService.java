package org.sanaa.youcode.redline.unirent.security.service;

import org.sanaa.youcode.redline.unirent.model.dto.Request.ChangePasswordDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppRole;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.model.mapper.UserMapper;
import org.sanaa.youcode.redline.unirent.repository.RoleRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService  implements  UserServiceI{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        AppRole role = roleRepository.findById(userRequestDTO.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        AppUser user = userMapper.toEntity(userRequestDTO);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        AppUser savedUser = userRepository.save(user);

        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(long id, UserRequestDTO userRequestDTO) {
        AppUser user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());

        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }

        AppUser updatedUser = userRepository.save(user);
        return userMapper.toResponseDto(updatedUser);
    }
    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {

    }
    @Override
    public UserResponseDTO getUserById(Long id) {
        AppUser user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponseDto(user);
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }


}
