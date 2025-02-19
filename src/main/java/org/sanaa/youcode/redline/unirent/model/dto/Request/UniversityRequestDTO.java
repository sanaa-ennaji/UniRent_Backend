package org.sanaa.youcode.redline.unirent.model.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityRequestDTO {
    private String name;
    private String city;
    private String contactInfo;
}
