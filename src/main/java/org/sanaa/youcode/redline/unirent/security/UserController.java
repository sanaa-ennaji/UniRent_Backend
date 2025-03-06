package org.sanaa.youcode.redline.unirent.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;
import org.sanaa.youcode.redline.unirent.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.registerUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }
}

