package org.sanaa.youcode.redline.unirent.model.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "support_tickets")
@Data
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String message;
    private String status; // open, resolved

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


}

