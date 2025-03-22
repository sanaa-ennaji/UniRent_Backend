package org.sanaa.youcode.redline.unirent.model.dto.Request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull
    private Long propertyId;
    private String status;
    @NotNull
    private Long studentId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;

}
