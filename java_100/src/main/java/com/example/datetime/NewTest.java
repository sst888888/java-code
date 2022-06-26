package com.example.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class NewTest {

    public static void main(String[] args) throws Exception {

        log.info("{}", new Date(0));
        log.info("{}", TimeZone.getDefault().getID() + ":" + TimeZone.getDefault().getRawOffset() / 3600000);
        wrong();
        right();
        better();
    }

    private static void wrong() {
        log.info("{}", "wrong");
        Date date = new Date(2019, 12, 31, 11, 12, 13);
        log.info("{}", date);
    }

    private static void wrongfix() {
        log.info("{}", "right");
        Date date = new Date(2019 - 1900, 11, 31, 11, 12, 13);
        log.info("{}", date);
    }

    private static void right() {
        log.info("{}", "right");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 11, 31, 11, 12, 13);
        log.info("{}", calendar.getTime());
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        calendar2.set(2019, Calendar.DECEMBER, 31, 11, 12, 13);
        log.info("{}", calendar2.getTime());
    }

    private static void better() {
        log.info("{}", "better");
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.DECEMBER, 31, 11, 12, 13);
        log.info("{}", localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("America/New_York"));
        log.info("{}", zonedDateTime);
    }

}
