package org.sanaa.youcode.redline.unirent.model.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.entity.Booking;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long propertyId;
    private String propertyTitle;
    private Long price ;
    private Long studentId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;


    public BookingResponseDTO(Booking booking) {
    }
}
