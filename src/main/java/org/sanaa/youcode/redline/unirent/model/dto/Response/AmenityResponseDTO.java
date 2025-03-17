package org.sanaa.youcode.redline.unirent.model.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.entity.AmenityProperty;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityResponseDTO {
    private Long id;
    private String name;
//    private List<AmenityProperty> amenityProperties;

}
