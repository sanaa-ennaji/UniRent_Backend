package org.sanaa.youcode.redline.unirent;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppRole;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.model.mapper.UserMapper;
import org.sanaa.youcode.redline.unirent.repository.RoleRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.sanaa.youcode.redline.unirent.security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setRoleId(1L);
        userRequestDTO.setPassword("password");

        AppRole role = new AppRole();
        role.setId(1L);
        role.setRoleName("USER");

        AppUser user = new AppUser();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("encodedPassword");
        user.setRole(role);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setName("John Doe");
        userResponseDTO.setEmail("john.doe@example.com");

        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));
        when(userMapper.toEntity(userRequestDTO)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponseDto(user)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.registerUser(userRequestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());

        verify(roleRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toEntity(userRequestDTO);
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toResponseDto(user);
    }
}
