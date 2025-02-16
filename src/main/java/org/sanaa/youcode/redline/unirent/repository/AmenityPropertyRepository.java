package org.sanaa.youcode.redline.unirent.repository;

import org.sanaa.youcode.redline.unirent.model.entity.AmenityProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityPropertyRepository extends JpaRepository<AmenityProperty, Long> {
}
