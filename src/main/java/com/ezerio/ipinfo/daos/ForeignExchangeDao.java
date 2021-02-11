package com.ezerio.ipinfo.daos;

import com.ezerio.ipinfo.entitys.ForeignExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForeignExchangeDao extends JpaRepository<ForeignExchange, Long>  {

    ForeignExchange getByCurrency(String currency);

}
