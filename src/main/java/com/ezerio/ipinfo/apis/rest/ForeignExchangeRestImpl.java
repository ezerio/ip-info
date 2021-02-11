package com.ezerio.ipinfo.apis.rest;

import com.ezerio.ipinfo.apis.dto.ForexApiDto;
import com.ezerio.ipinfo.apis.exception.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForeignExchangeRestImpl implements ForeignExchangeRest {

    public static final String URL_FOREX = "/latest?access_key=%s&symbols=%s";

    @Value("${ipinfo.forex.key}")
    private String key;

    @Autowired
    @Qualifier("api.foreign.exchange")
    private RestTemplate restTemplate;

    @Override
    public ForexApiDto getForeignExchange(String currency) {
        final String url = String.format(URL_FOREX, key, currency);
        ResponseEntity<ForexApiDto> response = restTemplate.getForEntity(url, ForexApiDto.class);
        ForexApiDto result = response.getBody();
        if(!result.getSuccess()) {
            throw new CurrencyNotFoundException();
        }
        return result;
    }
}
