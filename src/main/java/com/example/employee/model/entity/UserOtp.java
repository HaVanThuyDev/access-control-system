package com.example.employee.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "USER_OTP")
@Getter
@Setter
public class UserOtp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    @Column(name = "OTP_CODE", nullable = false)
    private String otpCode;
    @Column(name = "EXPIRED_AT", nullable = false)
    private LocalDateTime expiredAt;
    @Column(name = "USED")
    private Boolean used = false;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}
