package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;


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
    private String  description ;
    private String Type ;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private AppUser landlord;

    @ManyToMany(mappedBy = "properties")
    private List<University> universities = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<AmenityProperty> amenityProperties;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;


}

