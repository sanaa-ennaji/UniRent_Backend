package org.sanaa.youcode.redline.unirent.model.dto.Response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDTO {
    private Long id;
    private String title;
    private String address;
    private double price;
    private boolean available;
    private Long landlordId;
    private List<Long> universityIds;
    private List<AmenityResponseDTO> amenities;
    private List<String> imageUrls;
    private String landlordName;
//    private int personNumbers;
}
