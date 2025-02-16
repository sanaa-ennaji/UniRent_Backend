package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.sanaa.youcode.redline.unirent.model.dto.Response.SupportTicketResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.SupportTicket;

@Mapper(componentModel = "spring" )
public interface SupportTicketMapper {
    SupportTicketResponseDTO toResponseDTO(SupportTicket entity) ;
    SupportTicket toEntity(SupportTicketRequestDTO requestDTO);
    List<ImageResponseDTO> toResponseDTOList (List<Image> entities);
    List<Image> toEntityList(List<ImageRequestDTO> requestDTOs);
    void updateEntityFromRequest(AmenityRequestDTO amenityRequestDTO,@MappingTarget Image image);
}
