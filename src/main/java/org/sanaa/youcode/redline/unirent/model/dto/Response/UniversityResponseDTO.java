package org.sanaa.youcode.redline.unirent.model.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityResponseDTO {
    private Long id ;
    private String name;
    private String city;
    private Double latitude;
    private Double longitude;
}
