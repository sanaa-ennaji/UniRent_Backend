package org.sanaa.youcode.redline.unirent.security.config;

import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        AppUser user = new AppUser();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        AppUser savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhoneNumber(), savedUser.getRole().getId(), savedUser.getRole().getRoleName());
    }

    public UserResponseDTO getUserById(Long id) {
        AppUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRole().getId(), user.getRole().getRoleName());
    }
}
