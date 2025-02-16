package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.ImageRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ImageResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Image;

import java.util.List;


@Mapper(componentModel = "spring" )
public interface ImageMapper {
    ImageResponseDTO toResponseDTO(Image entity) ;
    Image toEntity(ImageRequestDTO requestDTO);
    List<ImageResponseDTO> toResponseDTOList (List<Image> entities);
    List<Image> toEntityList(List<ImageRequestDTO> requestDTOs);
    void updateEntityFromRequest(ImageRequestDTO imageRequestDTO,@MappingTarget Image image);
}
