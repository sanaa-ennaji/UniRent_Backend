package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long studentId;
    private Long propertyId;

    private String startDate;
    private String endDate;
    private String status; // Confirmed, Pending, Cancelled

    @ManyToOne
    @JoinColumn(name = "property_id", insertable = false, updatable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private User student;


}
