package org.sanaa.youcode.redline.unirent.model.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDTO {
    private Long id;
    private String imageUrl;
    private Long propertyId;
}
