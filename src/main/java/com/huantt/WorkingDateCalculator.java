package com.huantt;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huantt on 9/29/18
 */
public class WorkingDateCalculator {

    private int startWorkingDay;
    private int endWorkingDay;
    private int startWorkingHour;
    private int endWorkingHour;

    public WorkingDateCalculator() {
        startWorkingDay = Calendar.MONDAY;
        endWorkingDay = Calendar.FRIDAY;
        startWorkingHour = 8; //Start work at 08:00
        endWorkingHour = 18;//End work at 18:00
    }

    public WorkingDateCalculator(int startWorkingDay, int endWorkingDay, int startWorkingHour, int endWorkingHour) {
        this.startWorkingDay = startWorkingDay;
        this.endWorkingDay = endWorkingDay;
        this.startWorkingHour = startWorkingHour;
        this.endWorkingHour = endWorkingHour;
    }

    public WorkingDateCalculator(int startWorkingHour, int endWorkingHour) {
        this.startWorkingHour = startWorkingHour;
        this.endWorkingHour = endWorkingHour;
    }

    public int getWorkingHours(Date start, Date end){
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(start);
        endCal.setTime(end);
        return this.getWorkingHours(startCal, endCal);
    }


    public int getWorkingHours(Calendar start, Calendar end) {
        int workingSeconds = this.getWorkingSeconds(start, end);
        return (int) TimeUnit.SECONDS.toHours(workingSeconds);
    }

    public int getWorkingMinutes(Calendar start, Calendar end) {
        int workingSeconds = this.getWorkingSeconds(start, end);
        return (int) TimeUnit.SECONDS.toMinutes(workingSeconds);
    }

    public int getWorkingSeconds(Calendar start, Calendar end) {
        int workingSeconds = 0;
        while (start.getTimeInMillis() < end.getTimeInMillis()) {
            if (isWorkingDay(start) && isWorkingHour(start)) {
                workingSeconds++;
            }
            start.add(Calendar.SECOND, 1);
        }

        return workingSeconds;
    }

    private boolean isWorkingHour(Calendar calendar) { //TODO: Refactor this name
        return calendar.get(Calendar.HOUR_OF_DAY) >= startWorkingHour
                && calendar.get(Calendar.HOUR_OF_DAY) < endWorkingHour;
    }

    private boolean isWorkingDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) >= startWorkingDay
                && calendar.get(Calendar.DAY_OF_WEEK) <= endWorkingDay;
    }

    public int getStartWorkingDay() {
        return startWorkingDay;
    }

    public void setStartWorkingDay(int startWorkingDay) {
        this.startWorkingDay = startWorkingDay;
    }

    public int getEndWorkingDay() {
        return endWorkingDay;
    }

    public void setEndWorkingDay(int endWorkingDay) {
        this.endWorkingDay = endWorkingDay;
    }

    public int getStartWorkingHour() {
        return startWorkingHour;
    }

    public void setStartWorkingHour(int startWorkingHour) {
        this.startWorkingHour = startWorkingHour;
    }

    public int getEndWorkingHour() {
        return endWorkingHour;
    }

    public void setEndWorkingHour(int endWorkingHour) {
        this.endWorkingHour = endWorkingHour;
    }
}
