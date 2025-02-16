package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.ReviewRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ReviewResponseDTO;

import java.util.List;

public interface ReviewServiceI {
    ReviewResponseDTO getReviewById(Long id);
    List<ReviewResponseDTO> getAllReviews();
    ReviewResponseDTO createReview(ReviewRequestDTO requestDTO);
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO requestDTO);
    void deleteReview(Long id);
}
