package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ImageRequestDTO {
    @NotBlank
    private String imageUrl;

    @NotNull
    private Long propertyId;
}
