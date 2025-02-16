package org.sanaa.youcode.redline.unirent.service.ServiceI;

public interface AmenityServiceI {
    AmenityResponseDTO getAmenityById(Long id);
    List<AmenityResponseDTO> getAllAmenities();
    AmenityResponseDTO createAmenity(AmenityRequestDTO requestDTO);
    AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO requestDTO);
    void deleteAmenity(Long id);
}
