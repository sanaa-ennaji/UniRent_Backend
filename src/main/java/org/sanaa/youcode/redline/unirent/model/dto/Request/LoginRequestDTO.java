package org.sanaa.youcode.redline.unirent.model.dto.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    private String email;
    private String password;
}
