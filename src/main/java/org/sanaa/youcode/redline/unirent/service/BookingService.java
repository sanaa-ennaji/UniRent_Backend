package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.exception.ResourceNotFoundException;
import org.sanaa.youcode.redline.unirent.model.dto.Request.BookingRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.BookingResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.model.entity.Booking;
import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.sanaa.youcode.redline.unirent.model.mapper.BookingMapper;
import org.sanaa.youcode.redline.unirent.repository.BookingRepository;
import org.sanaa.youcode.redline.unirent.repository.PropertyRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.BookingServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookingService implements BookingServiceI {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;


    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, UserRepository userRepository, PropertyRepository propertyRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        return bookingMapper.toResponseDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingMapper.toResponseDTOList(bookingRepository.findAll());
    }

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        if (requestDTO.getStartDate().isAfter(requestDTO.getEndDate())) {
            throw new IllegalArgumentException("startDate must be before endDate");
        }

        Booking booking = bookingMapper.toEntity(requestDTO);

        AppUser student = userRepository.findById(requestDTO.getStudentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + requestDTO.getStudentId()));
        booking.setStudent(student);

        Property property = propertyRepository.findById(requestDTO.getPropertyId())
            .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + requestDTO.getPropertyId()));
        booking.setProperty(property);
        return bookingMapper.toResponseDTO(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO requestDTO) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingMapper.updateEntityFromRequest(requestDTO, booking);
        return bookingMapper.toResponseDTO(bookingRepository.save(booking));
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

