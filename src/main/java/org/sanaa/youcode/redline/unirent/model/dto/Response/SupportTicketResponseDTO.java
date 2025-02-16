package org.sanaa.youcode.redline.unirent.model.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicketResponseDTO {

    private Long id;
    private String subject;
    private String message;
    private String status;
    private Long userId;
    private String userName;
}
