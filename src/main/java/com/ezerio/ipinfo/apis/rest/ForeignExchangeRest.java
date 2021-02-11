package com.ezerio.ipinfo.apis.rest;

import com.ezerio.ipinfo.apis.dto.ForexApiDto;

public interface ForeignExchangeRest {

    public ForexApiDto getForeignExchange(String currency);

}
