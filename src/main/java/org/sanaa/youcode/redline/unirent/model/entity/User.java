package org.sanaa.youcode.redline.unirent.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings ;
    @OneToMany(mappedBy = "user")
    private List<Property> properties ;

    @OneToMany(mappedBy = "review")
    private List<Review> reviews ;

}
