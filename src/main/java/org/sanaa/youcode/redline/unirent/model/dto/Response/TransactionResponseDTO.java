package org.sanaa.youcode.redline.unirent.model.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    private Long id;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private Long bookingId;
}
