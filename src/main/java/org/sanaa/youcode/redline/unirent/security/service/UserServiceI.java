package org.sanaa.youcode.redline.unirent.security.service;

import org.sanaa.youcode.redline.unirent.model.dto.Request.ChangePasswordDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UserRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UserResponseDTO;

import java.util.List;

public interface UserServiceI {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getAllUsers();
    void delete(Long id );
    UserResponseDTO  updateUser(long id , UserRequestDTO userRequestDTO);
    void changePassword(ChangePasswordDTO changePasswordDTO);

}
