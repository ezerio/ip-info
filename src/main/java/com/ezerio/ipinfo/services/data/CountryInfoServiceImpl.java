package com.ezerio.ipinfo.services.data;

import com.ezerio.ipinfo.apis.dto.CountryInfoApiDto;
import com.ezerio.ipinfo.apis.dto.CountryInfoDto;
import com.ezerio.ipinfo.apis.dto.CurrencyDto;
import com.ezerio.ipinfo.apis.rest.CountryInfoRest;
import com.ezerio.ipinfo.daos.CountryInfoDao;
import com.ezerio.ipinfo.entitys.CountryInfo;
import com.ezerio.ipinfo.entitys.Currency;
import com.ezerio.ipinfo.utility.GeoCoordinate;
import com.ezerio.ipinfo.utility.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryInfoServiceImpl implements CountryInfoService {

    @Value("${ipinfo.country.info.minutes.update}")
    private long minutesToUpdate;

    @Value("${ipinfo.origin.latitude}")
    private int latitude;

    @Value("${ipinfo.origin.longitude}")
    private int longitude;

    @Autowired
    private CountryInfoRest countryInfoRest;

    @Autowired
    private CountryInfoDao countryInfoDao;

    @Override
    public CountryInfoDto getCountryInfo(String countryCode) {
        CountryInfo countryInfo = countryInfoDao.findByIsoCode(countryCode);
        if(getDataOnline(countryInfo)) {
            CountryInfoDto countryInfoDto = getInfoCountryOnline(countryCode);
            persistInfoCountry(countryInfoDto, countryInfo);
            return countryInfoDto;
        }
            return buildCountryInfo(countryInfo);
    }

    private boolean getDataOnline(CountryInfo countryInfo) {
        return countryInfo == null
                || TimeUtil.minutesGone(countryInfo.getLastUpdate()) >= minutesToUpdate;
    }

    private CountryInfoDto getInfoCountryOnline(String countryCode) {
        CountryInfoApiDto countryInfoApiDto = countryInfoRest.getInfoCountry(countryCode);
        return buildCountryInfo(countryInfoApiDto);
    }

    private CountryInfoDto buildCountryInfo(CountryInfo entity) {
        CountryInfoDto countryInfoDto = new CountryInfoDto();
        countryInfoDto.setCountry(entity.getCountry());
        countryInfoDto.setIsoCode(entity.getIsoCode());
        countryInfoDto.setEstimateDistance(entity.getEstimateDistance().toString());
        countryInfoDto.setCurrency(entity.getCurrency().stream()
                .map(d -> new CurrencyDto(d.getCode(), d.getSymbol()))
                .collect(Collectors.toList()));
        countryInfoDto.setLanguages(Arrays.asList(entity.getLanguage()));
        countryInfoDto.setTimes(Arrays.asList(entity.getTime()));
        return countryInfoDto;
    }

    private CountryInfoDto buildCountryInfo(CountryInfoApiDto apiDto) {
        CountryInfoDto countryInfoDto = new CountryInfoDto();
        countryInfoDto.setCountry(formatCountryName(apiDto));
        countryInfoDto.setIsoCode(apiDto.getAlpha2Code());
        countryInfoDto.setEstimateDistance(calculateDistance(apiDto).toString());
        countryInfoDto.setCurrency(apiDto.getCurrencies());
        countryInfoDto.setLanguages(formatLanguages(apiDto));
        countryInfoDto.setTimes(apiDto.getTimezones());
        return countryInfoDto;
    }

    private void persistInfoCountry(CountryInfoDto infoDto, CountryInfo countryInfo) {
        if(countryInfo == null) {
            countryInfo = new CountryInfo();
        }
        countryInfo.setCountry(infoDto.getCountry());
        countryInfo.setIsoCode(infoDto.getIsoCode());
        countryInfo.setLanguage(infoDto.getLanguages().toArray(new String[0]));
        countryInfo.setTime(infoDto.getTimes().toArray(new String[0]));
        countryInfo.setEstimateDistance(Integer.parseInt(infoDto.getEstimateDistance()));
        countryInfo.setCurrency(infoDto.getCurrency().stream()
                .map(d -> new Currency(d.getCode(), d.getSymbol()))
                .collect(Collectors.toList()));
        countryInfo.setLastUpdate(new Date());
        countryInfoDao.save(countryInfo);
    }

    private String formatCountryName(CountryInfoApiDto dto) {
        return String.format("%s (%s)", dto.getNativeName(), dto.getName());
    }

    private List<String> formatLanguages(CountryInfoApiDto dto) {
        return dto.getLanguages().stream()
                .map(data -> String.format("%s (%s)", data.getNativeName(), data.getIso639_1()))
                .collect(Collectors.toList());
    }

    private Integer calculateDistance(CountryInfoApiDto dto) {
       return new GeoCoordinate(dto.getLatlng()[0], dto.getLatlng()[1])
                .distanceTo(latitude, longitude);
    }

}