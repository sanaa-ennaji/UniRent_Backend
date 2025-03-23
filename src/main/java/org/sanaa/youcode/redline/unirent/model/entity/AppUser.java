package org.sanaa.youcode.redline.unirent.model.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppRole role;

    @OneToMany(mappedBy = "student")
    private List<Booking> bookings ;
    @OneToMany(mappedBy = "landlord")
    private List<Property> properties ;


}
