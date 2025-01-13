package org.sanaa.youcode.redline.unirent.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

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
    private String location;
    private String contactInfo;

    @OneToMany(mappedBy = "university")
    private List<Property> properties;



}

