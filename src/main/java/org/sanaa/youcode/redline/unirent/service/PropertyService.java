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

import java.time.LocalDate;
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
    private final AmenityRepository amenityRepository;

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
        AppUser landlord = userRepository.findById(requestDTO.getLandlordId())
            .orElseThrow(() -> new RuntimeException("Landlord not found"));
        property.setLandlord(landlord);

        Property savedProperty = propertyRepository.save(property);
        List<University> universities = universityRepository.findAllById(requestDTO.getUniversityIds());
        if (universities.size() != requestDTO.getUniversityIds().size()) {
            throw new RuntimeException("Some university IDs do not exist");
        }
        savedProperty.setUniversities(universities);
        for (University university : universities) {
            university.getProperties().add(savedProperty);
        }

        List<Amenity> amenities = amenityRepository.findAllById(requestDTO.getAmenityIds());
        if (amenities.size() != requestDTO.getAmenityIds().size()) {
            throw new RuntimeException("Some amenity IDs do not exist");
        }
        savedProperty.setAmenities(amenities);

        propertyRepository.save(savedProperty);

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

        propertyRepository.save(savedProperty);

        return propertyMapper.toResponseDTO(savedProperty);
    }

    @Override
    public List<PropertyResponseDTO> searchProperties(String title, Double price, LocalDate startDate) {
        List<Property> properties = propertyRepository.searchProperties(title, price, startDate);
        return propertyMapper.toResponseDTOList(properties);
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
    @Transactional
    public void delete(Long id) {
        Property property = propertyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Property not found"));

        if (property.getImages() != null && !property.getImages().isEmpty()) {
            property.getImages().clear();
        }

        if (property.getUniversities() != null && !property.getUniversities().isEmpty()) {
            for (University university : property.getUniversities()) {
                university.getProperties().remove(property);
            }
            property.getUniversities().clear();
        }

        if (property.getAmenities() != null && !property.getAmenities().isEmpty()) {
            for (Amenity amenity : property.getAmenities()) {
                amenity.getProperties().remove(property);
            }
            property.getAmenities().clear();
        }

        propertyRepository.save(property);

        propertyRepository.deleteById(id);
    }
}
