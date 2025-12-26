package com.example.employee.service.impl;

import com.example.employee.model.entity.UserDevice;
import com.example.employee.model.entity.UserOtp;
import com.example.employee.repository.UserDeviceRepository;
import com.example.employee.repository.UserOtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UserOtpRepository userOtpRepository;
    private final UserDeviceRepository userDeviceRepository;

    @Transactional
    public void verifyOtp(Long userId, String otp, String deviceHash) {

        UserOtp userOtp = userOtpRepository
                .findTopByUserIdAndOtpCodeAndUsedFalseOrderByCreatedAtDesc(userId, otp)
                .orElseThrow(() -> new RuntimeException("OTP không hợp lệ"));

        if (userOtp.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP hết hạn");
        }

        userOtp.setUsed(true);
        userOtpRepository.save(userOtp);

        UserDevice device = userDeviceRepository
                .findByUserIdAndDeviceHash(userId, deviceHash)
                .orElseThrow(() -> new RuntimeException("Thiết bị không tồn tại"));

        device.setTrusted(true);
        device.setLastLoginAt(LocalDateTime.now());
        userDeviceRepository.save(device);
    }
}
