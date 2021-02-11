package com.ezerio.ipinfo.services.data;

import com.ezerio.ipinfo.apis.dto.ForexApiDto;
import com.ezerio.ipinfo.apis.dto.ForexDto;
import com.ezerio.ipinfo.apis.rest.ForeignExchangeRest;
import com.ezerio.ipinfo.daos.ForeignExchangeDao;
import com.ezerio.ipinfo.entitys.ForeignExchange;
import com.ezerio.ipinfo.utility.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ForeignExchangeServiceImpl implements ForeignExchangeService {

    @Value("${ipinfo.forex.key}")
    private String key;

    @Value("${ipinfo.forex.minutes.update}")
    private int minutesToUpdate;

    @Autowired
    private ForeignExchangeRest foreignExchangeRest;

    @Autowired
    private ForeignExchangeDao foreignExchangeDao;

    @Override
    public Float getForeignExchange(String currency) {
        ForeignExchange foreignExchange = foreignExchangeDao.getByCurrency(currency);
        if(getDataOnline(foreignExchange)) {
            ForexDto forexDto = getForeignExchangeOnline(currency);
            updateRepoForeignExchange(forexDto, foreignExchange);
            return forexDto.getRate();
        }
       return foreignExchange.getRate();
    }

    private boolean getDataOnline(ForeignExchange foreignExchange) {
        return foreignExchange == null
                || TimeUtil.minutesGone(foreignExchange.getTimestamp()) >= minutesToUpdate;
    }

    private boolean isExpire(long date) {
        return TimeUtil.minutesGone(date) >= minutesToUpdate;
    }

    private ForexDto getForeignExchangeOnline(String currency) {
        ForexApiDto forexApiDto = foreignExchangeRest.getForeignExchange(currency);
        return buildForexDto(forexApiDto, currency);
    }

    private void updateRepoForeignExchange(ForexDto dto, ForeignExchange entity) {
        if(entity == null) {
            entity = new ForeignExchange();
            entity.setCurrency(dto.getCurrency());
        }
        entity.setRate(dto.getRate());
        entity.setTimestamp(dto.getTimestamp());
        foreignExchangeDao.save(entity);
    }

    private ForexDto buildForexDto(ForexApiDto result, String currency) {
        return new ForexDto(currency, result.getRates().get(currency), Long.parseLong(result.getTimestamp()));
    }

}