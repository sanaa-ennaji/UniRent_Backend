package org.sanaa.youcode.redline.unirent.model.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long id;
    private int rating;
    private String comments;
    private long propertyId;
    private Long userId;
}
