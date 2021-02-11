package com.ezerio.ipinfo.services;

import com.ezerio.ipinfo.daos.StatDao;
import com.ezerio.ipinfo.dtos.DistanceDto;
import com.ezerio.ipinfo.dtos.StatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;

    @Override
    public DistanceDto getStats() {
        List<StatDto> result = getCountryByGroup();
        DistanceDto distanceDto = new DistanceDto();
        distanceDto.setClosestDistance(getClosestDistance(result).toString());
        distanceDto.setFarthestDistance(getFarthestDistance(result).toString());
        distanceDto.setAverageDistance(getAverageDistance(result).toString());
        return distanceDto;
    }

    private List<StatDto> getCountryByGroup() {
        return statDao.countByCountry()
                .stream().map(obj -> (Object[]) obj)
                .map(StatDto::new)
                .collect(Collectors.toList());
    }

    private Integer getClosestDistance(List<StatDto> statDtos) {
        if(statDtos.size() == 0) {
            return 0;
        }
        return statDtos.get(0).getDistance();
    }

    private Integer getFarthestDistance(List<StatDto> statDtos) {
        final int size = statDtos.size();
        if(size == 0) {
            return 0;
        }
        return statDtos.get(size - 1).getDistance();
    }

    private Long getAverageDistance(List<StatDto> statDto) {
        long acumDistance = 0;
        long count = 0;
        for(StatDto dto : statDto) {
            acumDistance += dto.getDistance() * dto.getCount();
            count += dto.getCount();
        }
        if(count == 0) {
            return 0L;
        }
        return acumDistance / count;
    }


}
