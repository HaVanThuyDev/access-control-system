package com.example.employee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_DEVICE")
@Getter
@Setter
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;
    @Column(name = "USER_AGENT")
    private String userAgent;
    @Column(name = "DEVICE_HASH", nullable = false)
    private String deviceHash;
    @Column(name = "TRUSTED")
    private Boolean trusted = false;
    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLoginAt;
}
