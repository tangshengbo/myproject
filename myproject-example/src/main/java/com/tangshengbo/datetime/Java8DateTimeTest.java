package com.tangshengbo.datetime;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by Tangshengbo on 2018/6/20
 */
public class Java8DateTimeTest {

    private static final Logger logger = LoggerFactory.getLogger(Java8DateTimeTest.class);

    private LocalDate today = null;
    private LocalTime time = null;

    @Before
    public void init() {
        today = LocalDate.now();
        time = LocalTime.now();
    }

    @Test
    public void testDate() {
        logger.info("Today's Local date : " + today);
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        logger.info("{}", String.format("Year : %d  Month : %d  day : %d  %n", year, month, day));
        logger.info("==============================================================================");
        LocalDate date = LocalDate.of(2016, 6, 20);
        if (date.equals(today)) {
            logger.info("{}", String.format("Today %s and date1 %s are same date %n", today, date));
        } else {
            logger.info("{}", String.format("No Today %s and date1 %s are same date %n", today, date));
        }
        logger.info("==============================================================================");
        LocalDate dateOfBirth = LocalDate.of(2016, 6, 14);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if(currentMonthDay.equals(birthday)){
            logger.info("{}", "Many Many happy returns of the day !!");
        }else{
            logger.info("{}", "Sorry, today is not your birthday");
        }
        logger.info("{}", date.isBefore(dateOfBirth));
        logger.info("{}", date.isLeapYear());
    }

    @Test
    public void testTime() {
        time = time.minusHours(10);
        logger.info("{}", time.format(DateTimeFormatter.ofPattern("HH:mm:ss SSS")));
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        logger.info("{}", nextWeek);
    }

    @Test
    public void testClock() {
        Clock clock = Clock.systemUTC();
        logger.info("{}", clock.getZone());
        Clock defaultClock = Clock.systemDefaultZone();
        logger.info("{}", defaultClock.instant());
    }
}
