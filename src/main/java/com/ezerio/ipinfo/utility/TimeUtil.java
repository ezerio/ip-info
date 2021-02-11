package com.ezerio.ipinfo.utility;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {

    private final static DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final static DateTimeFormatter formatDatetime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String getCurrentTime(String zoneOf) {
        String value = zoneOf.substring(3);
        value = value.compareTo("") != 0 ? value : "+00:00";
        ZoneOffset zoneOffSet = ZoneOffset.of(value);
        String formattedDate = formatTime.format(OffsetDateTime.now(zoneOffSet));
        return String.format("%s (%s)", formattedDate, zoneOf);
    }

    public static String now() {
        return formatDatetime.format(LocalDateTime.now());
    }

    public static long minutesGone(Date dateFrom) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = Instant.ofEpochMilli(dateFrom.getTime())
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        return Math.abs(Duration.between(time, now).toMinutes());
    }

    public static long minutesGone(Long unixTime) {
        return ((Instant.now().toEpochMilli() / 1000) - unixTime) / 60;
    }

}

