package org.sanaa.youcode.redline.unirent.model.entity;


import jakarta.persistence.*;
import lombok.*;
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


}
