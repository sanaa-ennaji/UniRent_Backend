package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.SupportTicketRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.SupportTicketResponseDTO;

import java.util.List;

public interface SupportTicketServiceI {
    SupportTicketResponseDTO getTicketById(Long id);
    List<SupportTicketResponseDTO> getAllTickets();
    SupportTicketResponseDTO createTicket(SupportTicketRequestDTO requestDTO);
    SupportTicketResponseDTO updateTicket(Long id, SupportTicketRequestDTO requestDTO);
    void deleteTicket(Long id);
}
