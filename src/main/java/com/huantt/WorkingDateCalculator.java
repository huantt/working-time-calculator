package com.huantt;

import org.joda.time.LocalTime;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huantt on 9/29/18
 */
public class WorkingDateCalculator {

    private int startWorkingDay;
    private int endWorkingDay;
    private LocalTime startWorkingTime;
    private LocalTime endWorkingTime;
    private LocalTime startLunchTime;
    private LocalTime endLuchTime;


    public WorkingDateCalculator() {
        startWorkingDay = Calendar.MONDAY;
        endWorkingDay = Calendar.FRIDAY;
        startWorkingTime = new LocalTime(8, 0); //Start work at 08:00
        endWorkingTime = new LocalTime(17, 0);//End work at 18:00
        startLunchTime = new LocalTime(12, 0); //Start lunch time at 12:00
        endLuchTime = new LocalTime(13, 0);//End lunch time at 13:00
    }

    public WorkingDateCalculator(int startWorkingDay, int endWorkingDay, LocalTime startWorkingTime, LocalTime endWorkingTime, LocalTime startLunchTime, LocalTime endLuchTime) {
        this.startWorkingDay = startWorkingDay;
        this.endWorkingDay = endWorkingDay;
        this.startWorkingTime = startWorkingTime;
        this.endWorkingTime = endWorkingTime;
        this.startLunchTime = startLunchTime;
        this.endLuchTime = endLuchTime;
    }

    public int getWorkingHours(Date start, Date end) {
        int workingSeconds = this.getWorkingSeconds(start, end);
        return (int) TimeUnit.SECONDS.toHours(workingSeconds);
    }

    public int getWorkingMinutes(Date start, Date end) {
        int workingSeconds = this.getWorkingSeconds(start, end);
        return (int) TimeUnit.SECONDS.toMinutes(workingSeconds);
    }

    public int getWorkingSeconds(Date start, Date end) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(start);
        endCal.setTime(end);
        return this.getWorkingSeconds(startCal, endCal);
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
            if (isWorkingDay(start) && isWorkingTime(start)) {
                workingSeconds++;
            }
            start.add(Calendar.SECOND, 1);
        }

        return workingSeconds;
    }

    private boolean isWorkingTime(Calendar time) {
        LocalTime localTime = LocalTime.fromCalendarFields(time);
        return ((localTime.isAfter(this.startWorkingTime) || localTime.isEqual(this.startWorkingTime)) && (localTime.isBefore(startLunchTime) || localTime.isBefore(startLunchTime)))
                || ((localTime.isAfter(endLuchTime) || localTime.isEqual(endLuchTime)) && (localTime.isBefore(this.endWorkingTime) || localTime.isBefore(this.endWorkingTime)));
    }

    private boolean isWorkingDay(Calendar time) {
        return time.get(Calendar.DAY_OF_WEEK) >= startWorkingDay
                && time.get(Calendar.DAY_OF_WEEK) <= endWorkingDay;
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

    public LocalTime getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(LocalTime startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public LocalTime getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(LocalTime endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

    public LocalTime getStartLunchTime() {
        return startLunchTime;
    }

    public void setStartLunchTime(LocalTime startLunchTime) {
        this.startLunchTime = startLunchTime;
    }

    public LocalTime getEndLuchTime() {
        return endLuchTime;
    }

    public void setEndLuchTime(LocalTime endLuchTime) {
        this.endLuchTime = endLuchTime;
    }
}
