package org.sanaa.youcode.redline.unirent.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roleName;
    private String description;

    @OneToMany(mappedBy = "role")
    private  List<User> user ;


}
