package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.sanaa.youcode.redline.unirent.model.mapper.PropertyMapper;
import org.sanaa.youcode.redline.unirent.repository.PropertyRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.PropertyServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropertyService implements PropertyServiceI {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
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
        return propertyMapper.toResponseDTO(propertyRepository.save(property));
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
