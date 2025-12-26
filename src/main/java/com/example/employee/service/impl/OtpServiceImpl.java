package com.example.employee.service.impl;

import com.example.employee.model.entity.User;
import com.example.employee.model.entity.UserDevice;
import com.example.employee.model.entity.UserOtp;
import com.example.employee.repository.UserDeviceRepository;
import com.example.employee.repository.UserOtpRepository;
import com.example.employee.repository.UserRepository;
import com.example.employee.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final UserOtpRepository userOtpRepository;
    private final UserDeviceRepository userDeviceRepository;
    private final UserRepository userRepository;
    private final MailServiceImpl mailService;

    @Override
    @Transactional
    public void sendOtp(Long userId) {
        String otp = String.valueOf(
                ThreadLocalRandom.current().nextInt(100000, 999999)
        );
        UserOtp userOtp = new UserOtp();
        userOtp.setUserId(userId);
        userOtp.setOtpCode(otp);
        userOtp.setUsed(false);
        userOtp.setCreatedAt(LocalDateTime.now());
        userOtp.setExpiredAt(LocalDateTime.now().plusMinutes(5));
        userOtpRepository.save(userOtp);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User không tồn tại"));
        mailService.sendOtpMail(user.getGmail(), otp);
    }

    @Transactional
    @Override
    public void verifyOtp(Long userId, String otp, String deviceHash) {

        UserOtp userOtp = userOtpRepository.findTopByUserIdAndOtpCodeAndUsedFalseOrderByCreatedAtDesc(userId, otp).orElseThrow(()
        -> new RuntimeException("OTP không hợp lệ"));
        if (userOtp.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP hết hạn");
        }
        userOtp.setUsed(true);
        userOtpRepository.save(userOtp);
        UserDevice device = userDeviceRepository.findByUserIdAndDeviceHash(userId, deviceHash).orElseThrow(() -> new RuntimeException("Thiết bị không tồn tại"));
        device.setTrusted(true);
        device.setLastLoginAt(LocalDateTime.now());
        userDeviceRepository.save(device);
    }

}
