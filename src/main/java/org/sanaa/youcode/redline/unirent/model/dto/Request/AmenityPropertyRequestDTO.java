package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityPropertyRequestDTO {
    @NotNull
    @Min(0)
    private Integer quantity;
   // @NotNull
//    private Long propertyId;
    @NotNull
    private Long amenityId;
}
