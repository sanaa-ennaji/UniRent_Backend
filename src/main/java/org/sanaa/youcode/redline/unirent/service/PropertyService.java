package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PropertyRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.PropertyResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.*;
import org.sanaa.youcode.redline.unirent.model.mapper.PropertyMapper;
import org.sanaa.youcode.redline.unirent.repository.AmenityRepository;
import org.sanaa.youcode.redline.unirent.repository.PropertyRepository;
import org.sanaa.youcode.redline.unirent.repository.UniversityRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.PropertyServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyService implements PropertyServiceI {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final UniversityRepository universityRepository;
    private final UserRepository userRepository;
    private final AmenityRepository amenityRepository; // Inject AmenityRepository

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper, UniversityRepository universityRepository, UserRepository userRepository, AmenityRepository amenityRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
        this.universityRepository = universityRepository;
        this.userRepository = userRepository;
        this.amenityRepository = amenityRepository;
    }

    @Override
    public PropertyResponseDTO create(PropertyRequestDTO requestDTO) {

        Property property = propertyMapper.toEntity(requestDTO);

        List<University> universities = universityRepository.findAllById(requestDTO.getUniversityIds());
        if (universities.size() != requestDTO.getUniversityIds().size()) {
            throw new RuntimeException("Some university IDs do not exist");
        }

        property.setUniversities(universities);

        for (University university : universities) {
            university.getProperties().add(property);
        }

        AppUser landlord = userRepository.findById(requestDTO.getLandlordId())
            .orElseThrow(() -> new RuntimeException("Landlord not found"));
        property.setLandlord(landlord);
        Property savedProperty = propertyRepository.save(property);
        universityRepository.saveAll(universities);

        if (requestDTO.getImages() != null && !requestDTO.getImages().isEmpty()) {
            List<Image> images = requestDTO.getImages().stream()
                .map(imageDTO -> {
                    Image image = new Image();
                    image.setImageUrl(imageDTO.getImageUrl());
                    image.setProperty(savedProperty);
                    return image;
                })
                .collect(Collectors.toList());
            savedProperty.setImages(images);
        }

        if (requestDTO.getAmenityProperties() != null && !requestDTO.getAmenityProperties().isEmpty()) {
            List<AmenityProperty> amenityProperties = requestDTO.getAmenityProperties().stream()
                .map(amenityPropertyDTO -> {
                    AmenityProperty amenityProperty = new AmenityProperty();
                    amenityProperty.setQuantity(amenityPropertyDTO.getQuantity());

                    Amenity amenity = amenityRepository.findById(amenityPropertyDTO.getAmenityId())
                        .orElseThrow(() -> new RuntimeException("Amenity not found"));
                    amenityProperty.setAmenity(amenity);

                    amenityProperty.setProperty(savedProperty);
                    return amenityProperty;
                })
                .collect(Collectors.toList());
            savedProperty.setAmenityProperties(amenityProperties);
        }

        propertyRepository.save(savedProperty);

        return propertyMapper.toResponseDTO(savedProperty);
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
