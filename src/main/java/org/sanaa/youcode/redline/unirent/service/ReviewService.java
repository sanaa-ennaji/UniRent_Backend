package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.ReviewRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.ReviewResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Review;
import org.sanaa.youcode.redline.unirent.model.mapper.ReviewMapper;
import org.sanaa.youcode.redline.unirent.repository.ReviewRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.ReviewServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewService implements ReviewServiceI {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewResponseDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        return reviewMapper.toResponseDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewMapper.toResponseDTOList(reviewRepository.findAll());
    }

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO requestDTO) {
        Review review = reviewMapper.toEntity(requestDTO);
        return reviewMapper.toResponseDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO requestDTO) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewMapper.updateEntityFromRequest(requestDTO, review);
        return reviewMapper.toResponseDTO(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
