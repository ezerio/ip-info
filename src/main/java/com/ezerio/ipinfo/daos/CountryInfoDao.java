package com.ezerio.ipinfo.daos;

import com.ezerio.ipinfo.entitys.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryInfoDao extends JpaRepository<CountryInfo, Long> {

    CountryInfo findByIsoCode(String isoCode);
}
