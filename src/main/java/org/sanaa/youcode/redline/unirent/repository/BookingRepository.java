package org.sanaa.youcode.redline.unirent.repository;

import org.sanaa.youcode.redline.unirent.model.entity.Booking;
import org.sanaa.youcode.redline.unirent.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b JOIN b.property p WHERE p.landlord.id = :landlordId AND b.status = :status")
    List<Booking> findByLandlordIdAndStatus(@Param("landlordId") Long landlordId, @Param("status") Status status);
}
