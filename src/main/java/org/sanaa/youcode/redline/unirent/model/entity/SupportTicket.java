package org.sanaa.youcode.redline.unirent.model.entity;



import jakarta.persistence.*;
import lombok.*;
import org.sanaa.youcode.redline.unirent.model.enums.SupportTicketStatus;

@Entity
@Table(name = "support_tickets")
@Data
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String message;
    private SupportTicketStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


}

