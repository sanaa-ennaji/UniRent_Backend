package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "amenities")
@Data
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

}
