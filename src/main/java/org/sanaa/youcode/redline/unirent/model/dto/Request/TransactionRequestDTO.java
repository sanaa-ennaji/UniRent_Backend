package org.sanaa.youcode.redline.unirent.model.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    @Min(0)
    private double amount;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String paymentStatus;

    @NotNull
    private Long bookingId;
}
