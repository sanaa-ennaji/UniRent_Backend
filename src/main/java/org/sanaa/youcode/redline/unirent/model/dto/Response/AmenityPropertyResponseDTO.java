package org.sanaa.youcode.redline.unirent.model.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityPropertyResponseDTO {
    private Long id;
    private int quantity;
    private Long propertyId;
    private String propertyTitle;
    private Long amenityId;
    private String amenityName;
}
