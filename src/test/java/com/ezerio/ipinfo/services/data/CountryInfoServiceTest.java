package com.ezerio.ipinfo.services.data;

import com.ezerio.ipinfo.TestConfig;
import com.ezerio.ipinfo.apis.dto.CountryInfoApiDto;
import com.ezerio.ipinfo.apis.dto.CountryInfoDto;
import com.ezerio.ipinfo.apis.dto.CurrencyDto;
import com.ezerio.ipinfo.apis.dto.LanguageDto;
import com.ezerio.ipinfo.apis.rest.CountryInfoRest;
import com.ezerio.ipinfo.daos.CountryInfoDao;
import com.ezerio.ipinfo.entitys.CountryInfo;
import com.ezerio.ipinfo.entitys.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.Matchers.*;
import static  org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.BDDMockito.given;

@TestConfig
public class CountryInfoServiceTest {

    @Autowired
    private CountryInfoService countryInfoService;

    @Autowired
    private CountryInfoDao countryInfoDao;

    @MockBean
    private CountryInfoRest countryInfoRest;

    @BeforeEach
    public void setup() {
        countryInfoDao.deleteAll();
    }

    @Test
    public void existCountryInRepoTest() {
        countryInfoDao.save(buildCountryEntityUS());
        CountryInfoDto infoDto = countryInfoService.getCountryInfo("US");
        assertThat(true, equalTo(infoDto.getIsoCode().equals("US")));
    }

    @Test
    public void notExistCountryInRepoTest() {
        given(countryInfoRest.getInfoCountry("US")).willReturn(buildCountryUS());
        countryInfoService.getCountryInfo("US");
        CountryInfo resultRepo = countryInfoDao.findByIsoCode("US");
        assertThat(true, equalTo(resultRepo.getIsoCode().equals("US")));
    }

    private CountryInfo buildCountryEntityUS() {
        Currency currency = new Currency("USD", "$");
        CountryInfo countryInfo = new CountryInfo();
        countryInfo.setCountry("United States (United States of America)");
        countryInfo.setIsoCode("US");
        countryInfo.setEstimateDistance(8701);
        countryInfo.setLastUpdate(new Date());
        countryInfo.setTime(new String[] {"UTC-12:00"});
        countryInfo.setCurrency(Arrays.asList(new Currency[] {currency}));
        countryInfo.setLanguage(new String[] {"English (en)"});
        return countryInfo;
    }

    private CountryInfoApiDto buildCountryUS() {
        LanguageDto languageDto = new LanguageDto("en", "eng", "English","English");
        CurrencyDto currencyDto = new CurrencyDto("USD", "$");
        CountryInfoApiDto countryInfoApiDto = new CountryInfoApiDto();
        countryInfoApiDto.setAlpha2Code("US");
        countryInfoApiDto.setName("United States of America");
        countryInfoApiDto.setNativeName("United States");
        countryInfoApiDto.setTimezones(Arrays.asList(new String[] {"UTC-12:00"}));
        countryInfoApiDto.setLanguages(Arrays.asList(new LanguageDto[] {languageDto}));
        countryInfoApiDto.setCurrencies(Arrays.asList(new CurrencyDto[] {currencyDto}));
        countryInfoApiDto.setLatlng(new String[] {"38", "-97.0"});
        return countryInfoApiDto;
    }

}
