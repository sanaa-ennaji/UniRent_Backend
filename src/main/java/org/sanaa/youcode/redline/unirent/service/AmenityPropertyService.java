package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.exception.ResourceNotFoundException;
import org.sanaa.youcode.redline.unirent.model.dto.Request.AmenityPropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.AmenityPropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AmenityProperty;
import org.sanaa.youcode.redline.unirent.model.mapper.AmenityPropertyMapper;
import org.sanaa.youcode.redline.unirent.repository.AmenityPropertyRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.AmenityPropertyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AmenityPropertyService implements AmenityPropertyServiceI {
    private final AmenityPropertyRepository amenityPropertyRepository;
    private final AmenityPropertyMapper amenityPropertyMapper;

    @Autowired
    public AmenityPropertyService(AmenityPropertyRepository amenityPropertyRepository, AmenityPropertyMapper amenityPropertyMapper) {
        this.amenityPropertyRepository = amenityPropertyRepository;
        this.amenityPropertyMapper = amenityPropertyMapper;
    }

    @Override
    public AmenityPropertyResponseDTO getAmenityPropertyById(Long id) {
        AmenityProperty amenityProperty = amenityPropertyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("AmenityProperty not found"));
        return amenityPropertyMapper.toResponseDTO(amenityProperty);
    }

    @Override
    public List<AmenityPropertyResponseDTO> getAllAmenityProperties() {
        return amenityPropertyMapper.toResponseDTOList(amenityPropertyRepository.findAll());
    }

    @Override
    public AmenityPropertyResponseDTO createAmenityProperty(AmenityPropertyRequestDTO requestDTO) {
        AmenityProperty amenityProperty = amenityPropertyMapper.toEntity(requestDTO);
        return amenityPropertyMapper.toResponseDTO(amenityPropertyRepository.save(amenityProperty));
    }

    @Override
    public AmenityPropertyResponseDTO updateAmenityProperty(Long id, AmenityPropertyRequestDTO requestDTO) {
        AmenityProperty amenityProperty = amenityPropertyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("AmenityProperty not found"));
        amenityPropertyMapper.updateEntityFromRequest(requestDTO, amenityProperty);
        return amenityPropertyMapper.toResponseDTO(amenityPropertyRepository.save(amenityProperty));
    }

    @Override
    public void deleteAmenityProperty(Long id) {
        amenityPropertyRepository.deleteById(id);
    }
}
