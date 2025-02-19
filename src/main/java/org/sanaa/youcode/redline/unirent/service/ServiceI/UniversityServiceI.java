package org.sanaa.youcode.redline.unirent.service.ServiceI;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UniversityServiceI {
    Optional<UniversityResponseDTO> getById(Long id);
    List<UniversityResponseDTO> getAll();
    UniversityResponseDTO create(UniversityRequestDTO requestDTO);
    UniversityResponseDTO update(Long id, UniversityRequestDTO requestDTO);
    void delete(Long id);
}
