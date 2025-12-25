package com.example.employee.repository;

import com.example.employee.model.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {

    Optional<UserOtp> findTopByUserIdAndOtpCodeAndUsedFalseOrderByCreatedAtDesc(
            Long userId, String otpCode
    );
}
