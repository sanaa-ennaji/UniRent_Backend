package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
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

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

//    public void addImage(Image image) {
//        images.add(image);
//        image.setProperty(this);
//    }
//
//    public void removeImage(Image image) {
//        images.remove(image);
//        image.setProperty(null);
//    }

    @ManyToMany
    @JoinTable(
        name = "property_amenities",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;


}
