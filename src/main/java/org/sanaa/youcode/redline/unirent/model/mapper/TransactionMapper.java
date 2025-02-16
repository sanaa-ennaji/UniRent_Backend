package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.TransactionRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.TransactionResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.SupportTicket;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface TransactionMapper {
    TransactionResponseDTO toResponseDTO(SupportTicket entity) ;
    SupportTicket toEntity(TransactionRequestDTO requestDTO);
    List<TransactionResponseDTO> toResponseDTOList (List<SupportTicket> entities);
    List<SupportTicket> toEntityList(List<TransactionRequestDTO> requestDTOs);
    void updateEntityFromRequest(TransactionRequestDTO amenityRequestDTO,@MappingTarget SupportTicket supportTicket);
}
