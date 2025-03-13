package org.sanaa.youcode.redline.unirent.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "universities")
@Data
public class University {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city;
    private Double latitude;
    private Double longitude;

   // The properties and universities fields are initialized as new ArrayList<>()
   // to avoid NullPointerException.

    @ManyToMany
    @JoinTable(
        name = "university_property",
        joinColumns = @JoinColumn(name = "university_id"),
        inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<Property> properties = new ArrayList<>();

    public University(String name, String city, Double latitude, Double longitude) {
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

