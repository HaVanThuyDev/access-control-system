package com.example.employee.service;

public interface OtpService {
    void sendOtp(Long userId);
    void verifyOtp(Long userId, String otp, String deviceHash);
}
