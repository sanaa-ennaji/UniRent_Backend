package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany
    @JoinTable(
        name = "property_amenities",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;


}
