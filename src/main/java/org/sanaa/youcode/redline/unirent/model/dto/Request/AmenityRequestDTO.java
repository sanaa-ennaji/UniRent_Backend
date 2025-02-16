package org.sanaa.youcode.redline.unirent.model.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequestDTO {
    @NotNull
   private String name;
}
