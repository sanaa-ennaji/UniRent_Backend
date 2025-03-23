package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequestDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String address;
    @NotNull
    private double price;
    private boolean available;
    private Double latitude;
    private Double longitude;
    @NotNull
    @Min(value = 1, message = "Number of persons must be at least 1")
    @Max(value = 10, message = "Number of persons cannot exceed 10")
    private Integer personNumbers;

    @NotNull
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDate startDate;
    private String  description ;
    private String Type;
    @NotNull
    private Long landlordId;
    private List<Long> universityIds;
    private List<ImageRequestDTO> images;
    private List<Long> amenityIds;

}
