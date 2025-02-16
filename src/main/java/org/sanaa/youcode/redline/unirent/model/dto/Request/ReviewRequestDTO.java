package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    @NotBlank
    private int rating;
    @NotBlank
    private String comments;
    @NotNull
    private long propertyId;
    @NotNull
    private Long userId;
}
