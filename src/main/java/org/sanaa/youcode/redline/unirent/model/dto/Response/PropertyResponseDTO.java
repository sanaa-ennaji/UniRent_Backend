package org.sanaa.youcode.redline.unirent.model.dto.Response;

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
//    private String landlordName;
    private List<Long> universityIds;
//    private List<String> imageUrls;
}
