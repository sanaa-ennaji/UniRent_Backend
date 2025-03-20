package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    @NotNull
    private Long propertyId;

    @NotNull
    private Long studentId;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String gender;

}
