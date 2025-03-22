package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.sanaa.youcode.redline.unirent.model.enums.Status;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private AppUser student;
}
