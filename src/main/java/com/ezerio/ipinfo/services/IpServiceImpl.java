package com.ezerio.ipinfo.services;

import com.ezerio.ipinfo.apis.rest.IpCountryRest;
import com.ezerio.ipinfo.apis.dto.CountryInfoDto;
import com.ezerio.ipinfo.apis.dto.CurrencyDto;
import com.ezerio.ipinfo.daos.StatDao;
import com.ezerio.ipinfo.dtos.IpDto;
import com.ezerio.ipinfo.dtos.TraceDto;
import com.ezerio.ipinfo.entitys.Stat;
import com.ezerio.ipinfo.exceptions.InvalidIpException;
import com.ezerio.ipinfo.exceptions.IpNotFoundException;
import com.ezerio.ipinfo.services.data.CountryInfoService;
import com.ezerio.ipinfo.services.data.ForeignExchangeService;
import com.ezerio.ipinfo.utility.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private IpCountryRest ipCountryRest;

    @Autowired
    private CountryInfoService countryInfoService;

    @Autowired
    private ForeignExchangeService foreignExchangeService;

    @Autowired
    private StatDao statDao;

    @Override
    public TraceDto getIpInformation(IpDto ipDto) {
        if(!ipDto.isValid()) {
            throw new InvalidIpException();
        }
        String countryCode = getCountryCode(ipDto.getIp());
        CountryInfoDto countryInfoDto = countryInfoService.getCountryInfo(countryCode);
        TraceDto traceDto = build(countryInfoDto, ipDto.getIp());
        statDao.save(new Stat(countryInfoDto.getIsoCode()));
        return traceDto;
    }

    private String getCountryCode(String ip) {
        try {
            return ipCountryRest.getCountryCode(ip);
        } catch (Exception e) {
            throw new IpNotFoundException(ip);
        }
    }

    private TraceDto build(CountryInfoDto dto, String ip) {
        return TraceDto.builder()
                .ip(ip)
                .country(dto.getCountry())
                .isoCode(dto.getIsoCode())
                .language(dto.getLanguages())
                .estimateDistance(dto.getEstimateDistance() + " kms")
                .currency(calculateCurrency(dto.getCurrency()))
                .times(calculateTime(dto.getTimes()))
                .date(TimeUtil.now())
                .build();
    }

    private List<String> calculateTime(List<String> times) {
        return times.stream()
                .map(TimeUtil::getCurrentTime)
                .collect(Collectors.toList());
    }

    private List<String> calculateCurrency(List<CurrencyDto> currencies) {
        List<String> result = new ArrayList<>();
        for(CurrencyDto currency : currencies) {
            try {
                Float rate = foreignExchangeService.getForeignExchange(currency.getCode());
                String curr = String.format("%s (1 EUR = %s %s)", currency.getCode(), rate, currency.getSymbol());
                result.add(curr);
            } catch (Exception e) {
            }
        }
        return result;
    }


}