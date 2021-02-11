package com.ezerio.ipinfo.apis.rest;

import com.ezerio.ipinfo.apis.dto.CountryInfoApiDto;
import com.ezerio.ipinfo.apis.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryInfoRestImpl implements CountryInfoRest {

    private static final String COUNTRY_INFO = "/%s?fields=" +
            "nativeName;name;languages;timezones;currencies;alpha2Code;latlng";

    @Autowired
    @Qualifier("api.info.country")
    private RestTemplate restTemplate;

    @Override
    public CountryInfoApiDto getInfoCountry(String countryCode) {
        final String url = String.format(COUNTRY_INFO, countryCode);
        ResponseEntity<CountryInfoApiDto> response = restTemplate.getForEntity(url, CountryInfoApiDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new CountryNotFoundException();
        }
        return response.getBody();
    }
}
