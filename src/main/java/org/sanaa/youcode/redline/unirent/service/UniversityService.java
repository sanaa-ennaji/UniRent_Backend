package org.sanaa.youcode.redline.unirent.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.mapper.UniversityMapper;
import org.sanaa.youcode.redline.unirent.repository.UniversityRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.UniversityServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UniversityService implements UniversityServiceI {
    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    public UniversityService(UniversityRepository universityRepository, UniversityMapper universityMapper) {
        this.universityRepository = universityRepository;
        this.universityMapper = universityMapper;
    }

    @Override
    public UniversityResponseDTO getById(Long id) {
        return universityRepository.findById(id)
            .map(universityMapper)
    }

    @Override
    public List<UniversityResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public UniversityResponseDTO create(UniversityRequestDTO requestDTO) {

        return null;
    }

    @Override
    public UniversityResponseDTO update(Long id, UniversityRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if(!universityRepository.existsById(id)){
          throw new EntityNotFoundException("university not found " +id) ;
        }
        universityRepository.deleteById(id);

    }

}
