package org.sanaa.youcode.redline.unirent.service.ServiceI;

import org.sanaa.youcode.redline.unirent.model.dto.Request.BookingRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.BookingResponseDTO;

import java.util.List;

public interface BookingServiceI {
    BookingResponseDTO getBookingById(Long id);
    List<BookingResponseDTO> getAllBookings();
    BookingResponseDTO createBooking(BookingRequestDTO requestDTO);
    BookingResponseDTO updateBooking(Long id, BookingRequestDTO requestDTO);
    void deleteBooking(Long id);
}
