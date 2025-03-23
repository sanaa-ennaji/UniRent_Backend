package org.sanaa.youcode.redline.unirent.repository;

import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
        @Query("SELECT p FROM Property p WHERE " +
            "(:title IS NULL OR p.title LIKE %:title%) AND " +
            "(:price IS NULL OR p.price <= :price) AND " +
            "(:startDate IS NULL OR p.startDate >= :startDate)")
        List<Property> searchProperties(
            @Param("title") String title,
            @Param("price") Double price,
            @Param("startDate") LocalDate startDate
        );
    }

