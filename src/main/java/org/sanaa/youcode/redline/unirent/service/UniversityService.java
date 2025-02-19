package org.sanaa.youcode.redline.unirent.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.UniversityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.UniversityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.University;
import org.sanaa.youcode.redline.unirent.model.mapper.UniversityMapper;
import org.sanaa.youcode.redline.unirent.repository.UniversityRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.UniversityServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<UniversityResponseDTO> getById(Long id) {
        return universityRepository.findById(id)
            .map(universityMapper::toResponseDTO);
    }

    @Override
    public List<UniversityResponseDTO> getAll() {
        return universityRepository.findAll()
            .stream()
            .map(universityMapper::toResponseDTO)
            .toList();
    }

    @Override
    public UniversityResponseDTO create(UniversityRequestDTO requestDTO) {
        University university = universityMapper.toEntity(requestDTO);
        University savedUniversity = universityRepository.save(university);
        return universityMapper.toResponseDTO(savedUniversity);
    }

    @Override
    public UniversityResponseDTO update(Long id, UniversityRequestDTO requestDTO) {
         answer = answerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + id));
        answerMapper.updateEntityFromRequest(answerRequestDTO , answer);
        Answer updatedAnswer= answerRepository.save(answer);
        return answerMapper.toResponseDTO(updatedAnswer);
    }

    @Override
    public void delete(Long id) {
        if(!universityRepository.existsById(id)){
          throw new EntityNotFoundException("university not found " +id) ;
        }
        universityRepository.deleteById(id);

    }

}
