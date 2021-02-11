package com.ezerio.ipinfo.dtos;

import java.util.regex.Pattern;

public class IpDto {

    private static String ipv4_pattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private static String ipv6_pattern = "^(?:[A-F0-9]{1,4}:){7}[A-F0-9]{1,4}$";

    private static final Pattern IPV4_PATTERN = Pattern.compile(ipv4_pattern);
    private static final Pattern IPV6_PATTERN = Pattern.compile(ipv6_pattern);

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isValid() {
        return IPV4_PATTERN.matcher(ip).matches() || IPV6_PATTERN.matcher(ip).matches();
    }
}
