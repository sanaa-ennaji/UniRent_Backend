package org.sanaa.youcode.redline.unirent.repository;

import org.sanaa.youcode.redline.unirent.model.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
