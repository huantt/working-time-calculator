package com.huantt

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author huantt on 9/29/18
 */
class WorkingDateCalculatorTest extends Specification {

    @Unroll
    def "get working hours"() {
        when:
        WorkingDateCalculator workingDateCalculator = new WorkingDateCalculator()
        then:
        workingDateCalculator.getWorkingHours(startDate, endDate) == workingHours


        where:
        startDate                                                | endDate                                                  | workingHours
        Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 08:00:00") | Date.parse("yyyy-MM-dd HH:mm:ss", "2018-09-20 18:00:00") | 10
    }


}
