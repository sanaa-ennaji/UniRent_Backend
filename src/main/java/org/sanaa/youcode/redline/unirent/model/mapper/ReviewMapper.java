package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.ReviewRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ReviewResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface ReviewMapper {

    ReviewResponseDTO toResponseDTO(Review entity) ;
    Review toEntity(ReviewRequestDTO requestDTO);
    List<ReviewResponseDTO> toResponseDTOList (List<Review> entities);
    List<Review> toEntityList(List<ReviewRequestDTO> requestDTOs);
    void updateEntityFromRequest(ReviewRequestDTO reviewRequestDTO,@MappingTarget Review review);
}
