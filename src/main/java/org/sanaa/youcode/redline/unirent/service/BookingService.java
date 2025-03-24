package org.sanaa.youcode.redline.unirent.service;

import jakarta.transaction.Transactional;
import org.sanaa.youcode.redline.unirent.exception.ResourceNotFoundException;
import org.sanaa.youcode.redline.unirent.model.dto.Request.BookingRequestDTO;
import org.sanaa.youcode.redline.unirent.model.dto.Response.BookingResponseDTO;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.model.entity.Booking;
import org.sanaa.youcode.redline.unirent.model.entity.Property;
import org.sanaa.youcode.redline.unirent.model.enums.Status;
import org.sanaa.youcode.redline.unirent.model.mapper.BookingMapper;
import org.sanaa.youcode.redline.unirent.repository.BookingRepository;
import org.sanaa.youcode.redline.unirent.repository.PropertyRepository;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.sanaa.youcode.redline.unirent.service.ServiceI.BookingServiceI;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<BookingResponseDTO> getConfirmedBookingsByLandlordId(Long landlordId) {
        List<Booking> bookings = bookingRepository.findByLandlordIdAndStatus(landlordId, Status.CONFIRMED);
        return bookingMapper.toResponseDTOList(bookings);
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


    public BookingResponseDTO updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(Status.valueOf(status));
        bookingRepository.save(booking);

        return new BookingResponseDTO(booking);
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


    public List<Booking> findAll (){

        return  bookingRepository.findAll().stream()
            .filter(booking -> booking.getStatus()==Status.CONFIRMED)
            .collect(Collectors.toList());
    }
}

