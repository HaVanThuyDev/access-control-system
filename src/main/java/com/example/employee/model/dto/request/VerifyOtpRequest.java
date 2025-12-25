package com.example.employee.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpRequest {
    private Long userId;
    private String otp;
    private String deviceHash;
}
