package org.sanaa.youcode.redline.unirent.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.sanaa.youcode.redline.unirent.model.dto.Request.BookingRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.BookingResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.Booking;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface BookingMapper {
    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "property.title", target = "propertyTitle")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "property.price", target = "price")
    BookingResponseDTO toResponseDTO(Booking entity) ;

    @Mapping(source = "propertyId", target = "property.id")
    Booking toEntity(BookingRequestDTO requestDTO);
    List<BookingResponseDTO> toResponseDTOList (List<Booking> entities);
    List<Booking> toEntityList(List<BookingRequestDTO> requestDTOs);
    void updateEntityFromRequest(BookingRequestDTO amenityRequestDTO,@MappingTarget Booking booking);
}
