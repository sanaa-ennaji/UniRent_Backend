package org.sanaa.youcode.redline.unirent.model.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private String status;
    private Long propertyId;
    private String propertyTitle;
    private Long studentId;
    private String studentName;
}
