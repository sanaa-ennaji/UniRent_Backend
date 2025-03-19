package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String  description ;
    private String Type;
    @NotNull
    private Long landlordId;
    private List<Long> universityIds;
    private List<ImageRequestDTO> images;
    private List<Long> amenityIds;
}
