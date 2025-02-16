package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotNull
    private Long landlordId;

    @NotNull
    private Long universityId;
}
