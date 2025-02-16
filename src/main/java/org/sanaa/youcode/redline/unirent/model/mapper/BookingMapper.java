package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.sanaa.youcode.redline.unirent.model.dto.Request.BookingRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.BookingResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Booking;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface BookingMapper {

    BookingResponseDTO toResponseDTO(Booking entity) ;
    Booking toEntity(BookingRequestDTO requestDTO);
    List<BookingResponseDTO> toResponseDTOList (List<Image> entities);
    List<Booking> toEntityList(List<BookingRequestDTO> requestDTOs);
    void updateEntityFromRequest(BookingRequestDTO amenityRequestDTO,@MappingTarget Image image);
}
