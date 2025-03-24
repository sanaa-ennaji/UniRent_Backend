package org.sanaa.youcode.redline.unirent;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppRole;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.model.mapper.UserMapper;
import org.sanaa.youcode.redline.unirent.repository.RoleRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private org.sanaa.youcode.redline.unirent.security.service.UserService userService;

    @Test
    public void RegisterUser() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setRoleId(1L);
        userRequestDTO.setPassword("password");

        AppRole role = new AppRole();
        role.setId(1L);
        role.setRoleName("USER");

        AppUser user = new AppUser();
        user.setId(1L);
        user.setName("sanaa enng");
        user.setEmail("sanaa.enng@example.com");
        user.setPassword("encodedPassword");
        user.setRole(role);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setName("sanaa enng");
        userResponseDTO.setEmail("sanaa.enng@example.com");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userMapper.toEntity(userRequestDTO)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponseDto(user)).thenReturn(userResponseDTO);
        UserResponseDTO result = userService.registerUser(userRequestDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("sanaa enng", result.getName());
        assertEquals("sanaa.enng@example.com", result.getEmail());

        verify(roleRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toEntity(userRequestDTO);
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toResponseDto(user);
    }

    @Test
    public void RoleNotFound() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setRoleId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userRequestDTO);
        });

        assertEquals("Role not found", exception.getMessage());

        verify(roleRepository, times(1)).findById(1L);
        verify(userMapper, never()).toEntity(any());
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
        verify(userMapper, never()).toResponseDto(any());
    }
    @Test
    public void ExistingEmail() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setRoleId(1L);
        userRequestDTO.setEmail("existing@example.com");
        userRequestDTO.setPassword("password");

        AppRole role = new AppRole();
        role.setId(1L);
        role.setRoleName("USER");

        AppUser existingUser = new AppUser();
        existingUser.setId(1L);
        existingUser.setEmail("existing@example.com");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(existingUser));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userRequestDTO);
        });

        assertEquals("Email already exists", exception.getMessage());

        verify(roleRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findByEmail("existing@example.com");
        verify(userMapper, never()).toEntity(any());
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
        verify(userMapper, never()).toResponseDto(any());
    }


    @Test
    public void FindUserById() {

        Long userId = 1L;
        AppUser user = new AppUser();
        user.setId(userId);
        user.setName("sanaa enng");
        user.setEmail("sanaa.enng@example.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setName("sanaa enng");
        userResponseDTO.setEmail("sanaa.enng@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toResponseDto(user)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.getUserById(userId);


        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("sanaa enng", result.getName());
        assertEquals("sanaa.enng@example.com", result.getEmail());

        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).toResponseDto(user);
    }
}
