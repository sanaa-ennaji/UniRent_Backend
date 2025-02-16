package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.sanaa.youcode.redline.unirent.model.enums.Status;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String startDate;
    private String endDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private AppUser student;


}
