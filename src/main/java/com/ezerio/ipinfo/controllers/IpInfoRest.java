package com.ezerio.ipinfo.controllers;

import com.ezerio.ipinfo.dtos.DistanceDto;
import com.ezerio.ipinfo.dtos.IpDto;

import com.ezerio.ipinfo.dtos.TraceDto;
import com.ezerio.ipinfo.services.IpService;
import com.ezerio.ipinfo.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IpInfoRest.BASE_PATH)
public class IpInfoRest {

    public final static String BASE_PATH = "/ip";
    public final static String TRACE = "/trace";
    public final static String STATS = "/stats";

    @Autowired
    private IpService ipService;

    @Autowired
    private StatService statService;

    @PostMapping(TRACE)
    public ResponseEntity<TraceDto> getTrace(@RequestBody IpDto ipDto) {
        TraceDto traceDto = ipService.getIpInformation(ipDto);
        return new ResponseEntity<>(traceDto, HttpStatus.OK);
    }

    @GetMapping(STATS)
    public ResponseEntity<DistanceDto> stats() {
        DistanceDto distanceDto = statService.getStats();
        return new ResponseEntity<>(distanceDto, HttpStatus.OK);
    }

}
