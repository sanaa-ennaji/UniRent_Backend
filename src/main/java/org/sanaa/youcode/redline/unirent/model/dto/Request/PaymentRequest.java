package org.sanaa.youcode.redline.unirent.model.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Long amount;
    private String currency;
    private Long bookingId;
}
