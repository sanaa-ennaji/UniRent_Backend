package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

}
