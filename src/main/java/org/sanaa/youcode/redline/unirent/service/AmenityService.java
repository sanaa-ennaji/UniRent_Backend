package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Amenity;
import org.sanaa.youcode.redline.unirent.model.mapper.AmenityMapper;
import org.sanaa.youcode.redline.unirent.repository.AmenityRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.AmenityServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AmenityService implements AmenityServiceI {
    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    public AmenityService(AmenityRepository amenityRepository, AmenityMapper amenityMapper) {
        this.amenityRepository = amenityRepository;
        this.amenityMapper = amenityMapper;
    }

    @Override
    public Optional<AmenityResponseDTO> getById(Long id) {
      return amenityRepository.findById(id)
          .map(amenityMapper::toResponseDTO);
    }

    @Override
    public List<AmenityResponseDTO> getAll() {
        return amenityMapper.toResponseDTOList(amenityRepository.findAll());
    }

    @Override
    public AmenityResponseDTO create(AmenityRequestDTO requestDTO) {
        Amenity amenity = amenityMapper.toEntity(requestDTO);
        return amenityMapper.toResponseDTO(amenityRepository.save(amenity));
    }

    @Override
    public AmenityResponseDTO update(Long id, AmenityRequestDTO requestDTO) {
        Amenity amenity = amenityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Amenity not found"));
        amenityMapper.updateEntityFromRequest(requestDTO, amenity);
        return amenityMapper.toResponseDTO(amenityRepository.save(amenity));
    }

    @Override
    public void delete(Long id) {
        amenityRepository.deleteById(id);
    }
}
