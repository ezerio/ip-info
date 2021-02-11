package com.ezerio.ipinfo.services;

import com.ezerio.ipinfo.dtos.IpDto;
import com.ezerio.ipinfo.dtos.TraceDto;

public interface IpService {

    public TraceDto getIpInformation(IpDto ip);

}
