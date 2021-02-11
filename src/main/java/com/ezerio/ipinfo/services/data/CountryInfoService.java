package com.ezerio.ipinfo.services.data;

import com.ezerio.ipinfo.apis.dto.CountryInfoDto;

public interface CountryInfoService {

    public CountryInfoDto getCountryInfo(String countryCode);

}
