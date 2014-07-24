package com.nokia.mp.testdatacenter.utils;

import java.sql.Timestamp;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Utils {
    private Utils() {

    }

    public static Timestamp str2Timestamp(String time) {
        Timestamp ts = Timestamp.valueOf(time);
        return ts;
    }

    /* it take a string like "2014-7-14 00:00:00"
     * it return a long like 1406131200000
     * it is used to compare two times
     */
    public static long str2timeLong(String time) {
        Timestamp ts = Timestamp.valueOf(time);
    return ts.getTime();
    }


    /* it return week number
     * input: "2014-7-14"
     * return 29
     */
    static public int getWeekOfYear(String date){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(Calendar.SUNDAY); // from sunday
            cal.setMinimalDaysInFirstWeek(1); // at east one day
            cal.setTime(df.parse(date));
            return cal.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    static private Calendar getCalendarFormYear(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        //cal.setFirstDayOfWeek(Calendar.SUNDAY); // from sunday
        cal.setMinimalDaysInFirstWeek(1); // at east one day
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /* it return a stirng like "2014-7-13"
     * that represent first day of 2014wk29
     * input: year:2014, weekNo:29
     */
    static public String getStartDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);

    }

    /* it return a stirng like "2014-7-19"
     * that represent last day of 2014wk29
     * input: year:2014, weekNo:29
     */
    static public String getEndDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }
}
