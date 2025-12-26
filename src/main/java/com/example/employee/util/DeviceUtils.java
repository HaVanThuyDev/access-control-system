package com.example.employee.util;

import org.apache.commons.codec.digest.DigestUtils;

public final class DeviceUtils {

    private DeviceUtils() {

    }

    public static String generateDeviceHash(Long userId, String ip, String userAgent) {
        if (userAgent == null) userAgent = "unknown";
        String ipPrefix = ip.contains(".")
                ? ip.substring(0, ip.lastIndexOf("."))
                : ip;
        String raw = userId + "|" + ipPrefix + "|" + userAgent;
        return DigestUtils.sha256Hex(raw);
    }
}
