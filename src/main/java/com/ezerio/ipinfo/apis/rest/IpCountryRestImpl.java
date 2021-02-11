package com.ezerio.ipinfo.apis.rest;

import com.ezerio.ipinfo.apis.dto.IpInfoDto;
import com.ezerio.ipinfo.apis.exception.IpNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IpCountryRestImpl implements IpCountryRest {

    private static final String URL_IP = "/ip?%s";

    @Autowired
    @Qualifier("api.ip.country")
    private RestTemplate restTemplate;

    @Override
    public String getCountryCode(String ip) {
        final String url = String.format(URL_IP, ip);
        ResponseEntity<IpInfoDto> response = restTemplate.getForEntity(url, IpInfoDto.class);
        if(response.getStatusCode() != HttpStatus.OK
                || response.getBody().getCountryCode().isEmpty()) {
            throw new IpNotFoundException();
        }
        return response.getBody().getCountryCode();
    }

}
