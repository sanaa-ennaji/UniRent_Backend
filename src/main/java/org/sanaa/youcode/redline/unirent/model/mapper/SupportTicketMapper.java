package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.sanaa.youcode.redline.unirent.model.dto.Request.SupportTicketRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.SupportTicketResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.SupportTicket;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface SupportTicketMapper {
    SupportTicketResponseDTO toResponseDTO(SupportTicket entity) ;
    SupportTicket toEntity(SupportTicketRequestDTO requestDTO);
    List<SupportTicketResponseDTO> toResponseDTOList (List<Image> entities);
    List<> toEntityList(List<ImageRequestDTO> requestDTOs);
    void updateEntityFromRequest(AmenityRequestDTO amenityRequestDTO,@MappingTarget Image image);
}
