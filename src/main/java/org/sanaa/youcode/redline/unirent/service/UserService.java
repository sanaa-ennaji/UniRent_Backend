package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.exception.DuplicatedException;
import org.sanaa.youcode.redline.unirent.exception.ResourceNotFoundException;
import org.sanaa.youcode.redline.unirent.model.dto.Request.ChangePasswordDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Request.LoginRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.LoginResponseDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppRole;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.model.mapper.UserMapper;
import org.sanaa.youcode.redline.unirent.repository.RoleRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.sanaa.youcode.redline.unirent.security.config.JwtUtils;
import org.sanaa.youcode.redline.unirent.service.ServiceI.UserServiceI;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceI {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public final AuthenticationManager authenticationManager;
    public final JwtUtils jwtUtils;
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        AppUser user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponseDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw  new DuplicatedException("email alreaday exists");
        }
        AppRole role = roleRepository.findById(userRequestDTO.getRoleId())
            .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        AppUser user = userMapper.toEntity(userRequestDTO);
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setRole(role);
        return userMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        AppUser user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntityFromRequest(requestDTO, user);
        return userMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );

        String token = jwtUtils.generateToken(loginRequest.getEmail());
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setEmail(authentication.getName());
        response.setRole(authentication.getAuthorities().iterator().next().getAuthority());
        return response;

    }


    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        if(changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) {
            throw new BadCredentialsException("Le nouveau mot de passe ne peut pas être identique à l'ancien mot de passe.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        AppUser user = userRepository.findByUsername(userAuth)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Ancien mot de passe incorrect");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }



}
