package com.huantt

import org.joda.time.LocalTime
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

/**
 * @author huantt on 9/29/18
 */
class WorkingDateCalculatorTest extends Specification {

    @Unroll
    def "get working seconds (Date start, Date end)"() {
        when:
        WorkingDateCalculator workingDateCalculator = new WorkingDateCalculator()
        workingDateCalculator.setStartLunchTime(new LocalTime(12,0))
        workingDateCalculator.setEndLuchTime(new LocalTime(13,0))

        then:
        workingDateCalculator.getWorkingSeconds(startDate, endDate) == workingSeconds.toInteger()

        where:
        startDate                                                | endDate                                                  | workingSeconds
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-19 17:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | TimeUnit.HOURS.toSeconds(0)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 06:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | TimeUnit.HOURS.toSeconds(0)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 06:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:30:00") | TimeUnit.MINUTES.toSeconds(30)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | TimeUnit.HOURS.toSeconds(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:30:00") | TimeUnit.HOURS.toSeconds(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | TimeUnit.HOURS.toSeconds(0)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:30:00") | TimeUnit.MINUTES.toSeconds(30)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 17:00:00") | TimeUnit.HOURS.toSeconds(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 20:00:00") | TimeUnit.HOURS.toSeconds(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 16:01:00") | TimeUnit.HOURS.toSeconds(11) + TimeUnit.MINUTES.toSeconds(1)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 16:01:01") | TimeUnit.HOURS.toSeconds(11) + TimeUnit.MINUTES.toSeconds(1) + 1
    }


    @Unroll
    def "get working minutes (Date start, Date end)"() {
        when:
        WorkingDateCalculator workingDateCalculator = new WorkingDateCalculator()
        workingDateCalculator.setStartLunchTime(new LocalTime(12,0))
        workingDateCalculator.setEndLuchTime(new LocalTime(13,0))

        then:
        workingDateCalculator.getWorkingMinutes(startDate, endDate) == workingMinutes.toInteger()

        where:
        startDate                                                | endDate                                                  | workingMinutes
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-19 17:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | TimeUnit.HOURS.toMinutes(0)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 06:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | TimeUnit.HOURS.toMinutes(0)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 06:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:30:00") | 30
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | TimeUnit.HOURS.toMinutes(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:30:00") | TimeUnit.HOURS.toMinutes(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | TimeUnit.HOURS.toMinutes(0)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:30:00") | 30
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 17:00:00") | TimeUnit.HOURS.toMinutes(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 20:00:00") | TimeUnit.HOURS.toMinutes(4)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 16:01:00") | TimeUnit.HOURS.toMinutes(11) + 1
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 16:01:01") | TimeUnit.HOURS.toMinutes(11) + 1
    }

    @Unroll
    def "get working hours (Date start, Date end)"() {
        when:
        WorkingDateCalculator workingDateCalculator = new WorkingDateCalculator()
        workingDateCalculator.setStartLunchTime(new LocalTime(12,0))
        workingDateCalculator.setEndLuchTime(new LocalTime(13,0))

        then:
        workingDateCalculator.getWorkingHours(startDate, endDate) == workingHours

        where:
        startDate                                                | endDate                                                  | workingHours
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-19 17:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | 0
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 06:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | 0
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 06:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:30:00") | 0
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | 4
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:30:00") | 4
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | 0
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 12:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:30:00") | 0
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 17:00:00") | 4
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 20:00:00") | 4
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 16:01:00") | 11
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 13:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 16:01:01") | 11
    }

}
