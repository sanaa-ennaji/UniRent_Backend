package org.sanaa.youcode.redline.unirent.model.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate startDate;
    private Double latitude;
    private Double longitude;
    private Long landlordId;
    private List<Long> universityIds;
    private List<AmenityResponseDTO> amenities;
    private List<String> imageUrls;
    private String landlordName;
    private int personNumbers;
}
