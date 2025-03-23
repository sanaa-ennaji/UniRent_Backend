package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "properties")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String address;
    private double price;
    private boolean available;

    private LocalDate startDate;
    private String  description ;
    private String Type ;
    private int personNumbers;
    private Double latitude;
    private Double longitude;
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private AppUser landlord;

    @ManyToMany(mappedBy = "properties")
    private List<University> universities = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @ManyToMany
    @JoinTable(
        name = "property_amenity",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities = new ArrayList<>();

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;


}

