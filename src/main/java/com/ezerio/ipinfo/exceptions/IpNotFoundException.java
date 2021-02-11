package com.ezerio.ipinfo.exceptions;

public class IpNotFoundException extends InvalidIpException.NotFoundException {

    public IpNotFoundException(String ip) {
        super("Ip not found: " + ip);
    }

}
