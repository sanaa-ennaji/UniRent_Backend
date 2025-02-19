package org.sanaa.youcode.redline.unirent.service.ServiceI;



import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UniversityService {
    UniversityResponseDTO getUserById(Long id);
    List<UniversityResponseDTO> getAllUsers();
    UniversityResponseDTO createUser(UniversityRequestDTO requestDTO);
    UniversityResponseDTO updateUser(Long id, UniversityRequestDTO requestDTO);
    void deleteUser(Long id);
}
