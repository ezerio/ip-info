package com.ezerio.ipinfo.daos;

import com.ezerio.ipinfo.entitys.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatDao extends JpaRepository<Stat, Long> {

    @Query(value = "select s.country_code, c.estimated_distance, count(*)\n" +
            "from stats s\n" +
            "join country_info c on s.country_code = c.iso_code\n" +
            "group by country_code, c.estimated_distance\n" +
            "order by c.estimated_distance asc", nativeQuery = true)
    List<Object> countByCountry();

}
