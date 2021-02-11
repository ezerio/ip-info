package com.ezerio.ipinfo.utility;

import com.ezerio.ipinfo.TestConfig;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import static  org.hamcrest.MatcherAssert.assertThat;

@TestConfig
public class GeoCoordinateTest {

    @Test
    public void distanceTest() {
        GeoCoordinate bsAsCoordinate = new GeoCoordinate(-34, -64);
        double result = bsAsCoordinate.distanceTo(40, -4);
        double expect = 10270;
        assertThat(result, is(closeTo(expect, 10)));
    }


}

