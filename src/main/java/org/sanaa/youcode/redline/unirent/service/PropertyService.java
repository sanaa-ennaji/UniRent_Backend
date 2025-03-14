package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.sanaa.youcode.redline.unirent.model.entity.University;
import org.sanaa.youcode.redline.unirent.model.mapper.PropertyMapper;
import org.sanaa.youcode.redline.unirent.repository.PropertyRepository;
import org.sanaa.youcode.redline.unirent.repository.UniversityRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.PropertyServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropertyService implements PropertyServiceI {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final UniversityRepository  universityRepository;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper, UniversityRepository universityRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<PropertyResponseDTO> getById(Long id) {
        return propertyRepository.findById(id)
            .map(propertyMapper::toResponseDTO);
    }


    @Override
    public List<PropertyResponseDTO> getAll() {
        return propertyMapper.toResponseDTOList(propertyRepository.findAll());
    }

    @Override
    public PropertyResponseDTO create(PropertyRequestDTO requestDTO) {
        Property property = propertyMapper.toEntity(requestDTO);

        if (requestDTO.getUniversityIds() != null && !requestDTO.getUniversityIds().isEmpty()) {
            List<University> universities = universityRepository.findAllById(requestDTO.getUniversityIds());
            property.setUniversities(universities);

            for (University university : universities) {
                university.getProperties().add(property);
            }
        }
        Property savedProperty = propertyRepository.save(property);
        return propertyMapper.toResponseDTO(savedProperty);
    }


    @Override
    public PropertyResponseDTO update(Long id, PropertyRequestDTO requestDTO) {
        Property property = propertyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Property not found"));
        propertyMapper.updateEntityFromRequest(requestDTO, property);
        return propertyMapper.toResponseDTO(propertyRepository.save(property));
    }

    @Override
    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }
}
