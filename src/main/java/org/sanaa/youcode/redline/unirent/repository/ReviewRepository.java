package org.sanaa.youcode.redline.unirent.repository;

import org.sanaa.youcode.redline.unirent.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
