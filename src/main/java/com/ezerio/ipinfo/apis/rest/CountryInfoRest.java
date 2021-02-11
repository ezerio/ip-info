package com.ezerio.ipinfo.apis.rest;

import com.ezerio.ipinfo.apis.dto.CountryInfoApiDto;

public interface CountryInfoRest {

    public CountryInfoApiDto getInfoCountry(String countryCode);
}
