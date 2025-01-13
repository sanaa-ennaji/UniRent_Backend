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

    private Long userId;
    private String subject;
    private String message;
    private String status; // Open, Resolved

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


}

