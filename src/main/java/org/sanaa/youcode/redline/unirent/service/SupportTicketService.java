package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.model.dto.Request.SupportTicketRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.SupportTicketResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.SupportTicket;
import org.sanaa.youcode.redline.unirent.model.mapper.SupportTicketMapper;
import org.sanaa.youcode.redline.unirent.repository.SupportTicketRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.SupportTicketServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SupportTicketService  implements SupportTicketServiceI {

    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public SupportTicketService(SupportTicketRepository supportTicketRepository, SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public SupportTicketResponseDTO getTicketById(Long id) {
        SupportTicket ticket = supportTicketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return supportTicketMapper.toResponseDTO(ticket);
    }

    @Override
    public List<SupportTicketResponseDTO> getAllTickets() {
        return supportTicketMapper.toResponseDTOList(supportTicketRepository.findAll());
    }

    @Override
    public SupportTicketResponseDTO createTicket(SupportTicketRequestDTO requestDTO) {
        SupportTicket ticket = supportTicketMapper.toEntity(requestDTO);
        return supportTicketMapper.toResponseDTO(supportTicketRepository.save(ticket));
    }

    @Override
    public SupportTicketResponseDTO updateTicket(Long id, SupportTicketRequestDTO requestDTO) {
        SupportTicket ticket = supportTicketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
        supportTicketMapper.updateEntityFromRequest(requestDTO, ticket);
        return supportTicketMapper.toResponseDTO(supportTicketRepository.save(ticket));
    }

    @Override
    public void deleteTicket(Long id) {
        supportTicketRepository.deleteById(id);
    }

}
