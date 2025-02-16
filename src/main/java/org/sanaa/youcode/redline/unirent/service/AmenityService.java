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
    public AmenityResponseDTO getAmenityById(Long id) {
        Amenity amenity = amenityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Amenity not found"));
        return amenityMapper.toResponseDTO(amenity);
    }

    @Override
    public List<AmenityResponseDTO> getAllAmenities() {
        return amenityMapper.toResponseDTOList(amenityRepository.findAll());
    }

    @Override
    public AmenityResponseDTO createAmenity(AmenityRequestDTO requestDTO) {
        Amenity amenity = amenityMapper.toEntity(requestDTO);
        return amenityMapper.toResponseDTO(amenityRepository.save(amenity));
    }

    @Override
    public AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO requestDTO) {
        Amenity amenity = amenityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Amenity not found"));
        amenityMapper.updateEntityFromRequest(requestDTO, amenity);
        return amenityMapper.toResponseDTO(amenityRepository.save(amenity));
    }

    @Override
    public void deleteAmenity(Long id) {
        amenityRepository.deleteById(id);
    }
}
