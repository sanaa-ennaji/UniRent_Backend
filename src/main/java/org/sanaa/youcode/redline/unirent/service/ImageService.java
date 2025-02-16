package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.ImageRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ImageResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Image;
import org.sanaa.youcode.redline.unirent.model.mapper.ImageMapper;
import org.sanaa.youcode.redline.unirent.repository.ImageRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.ImageServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ImageService implements ImageServiceI {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public ImageResponseDTO getImageById(Long id) {
        Image image = imageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Image not found"));
        return imageMapper.toResponseDTO(image);
    }

    @Override
    public List<ImageResponseDTO> getAllImages() {
        return imageMapper.toResponseDTOList(imageRepository.findAll());
    }

    @Override
    public ImageResponseDTO uploadImage(ImageRequestDTO requestDTO) {
        Image image = imageMapper.toEntity(requestDTO);
        return imageMapper.toResponseDTO(imageRepository.save(image));
    }

    @Override
    public ImageResponseDTO updateImage(Long id, ImageRequestDTO requestDTO) {
        Image image = imageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Image not found"));
        imageMapper.updateEntityFromRequest(requestDTO, image);
        return imageMapper.toResponseDTO(imageRepository.save(image));
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
