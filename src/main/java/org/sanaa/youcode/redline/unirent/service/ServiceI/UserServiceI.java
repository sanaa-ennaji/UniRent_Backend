package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.ChangePasswordDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Request.LoginRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ResponseLoginDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;

import java.util.List;

public interface UserServiceI {
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createUser(UserRequestDTO requestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO);
    void deleteUser(Long id);
    void changePassword(ChangePasswordDTO changePasswordDTO);
    ResponseLoginDTO login(LoginRequestDTO loginRequest);
}
