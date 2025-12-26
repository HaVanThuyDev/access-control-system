package com.example.employee.model.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {
    private boolean requireOtp;
    private String token;
    private String deviceHash;
}
