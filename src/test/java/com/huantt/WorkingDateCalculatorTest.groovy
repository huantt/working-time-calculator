package com.huantt

import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

/**
 * @author huantt on 9/29/18
 */
class WorkingDateCalculatorTest extends Specification {

    @Unroll
    def "get working hours"() {
        when:
        WorkingDateCalculator workingDateCalculator = new WorkingDateCalculator()
        then:
        workingDateCalculator.getWorkingSeconds(startDate, endDate) == workingHours

        where:
        startDate                                                | endDate                                                  | workingHours
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 17:00:00") | TimeUnit.HOURS.toSeconds(8)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 17:00:00") | TimeUnit.HOURS.toSeconds(16)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 15:00:00") | TimeUnit.HOURS.toSeconds(14)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 15:01:00") | TimeUnit.HOURS.toSeconds(14) + TimeUnit.MINUTES.toSeconds(1)
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-21 15:00:01") | TimeUnit.HOURS.toSeconds(14) + 1
    }


}
