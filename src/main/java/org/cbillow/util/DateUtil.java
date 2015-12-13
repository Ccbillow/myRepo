/**
 * Baidu.com Inc. Copyright (c) 2000-2014 All Rights Reserved.
 */
package org.cbillow.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期通用工具类
 *
 * @author yaoxi.wang
 */
public class DateUtil {

    private static final long DAY = 24 * 60 * 60 * 1000;
    public static final String YYYYMMDD = "YYYYMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_HMS = "HH:mm:ss";
    public static final String PATTERN_HM = "HH:mm";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static DateTimeFormatter getFormatter(String pattern) {
        if (StringUtils.isNotEmpty(pattern)) {
            return DateTimeFormat.forPattern(pattern);
        }
        return null;
    }

    public static DateTime toDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime().withMillis(date.getTime());
    }

    public static DateTime format(String date) {
        if (StringUtils.isNotEmpty(date)) {
            return format(DEFAULT_DATE_TIME_PATTERN, date);
        }
        return null;
    }

    public static DateTime format(String pattern, String date) {
        if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(pattern)) {
            return getFormatter(pattern).parseDateTime(date);
        }
        return null;
    }

    public static Date format2Date(String date) {
        if (StringUtils.isNotEmpty(date)) {
            return format(date).toDate();
        }
        return null;
    }

    public static Date format2Date(String pattern, String date) {
        if (StringUtils.isNotEmpty(pattern) && StringUtils.isNotEmpty(date)) {
            return format(pattern, date).toDate();
        }
        return null;
    }

    public static Date getZeroPointOfDay(Long millions) {
        if (millions == null) {
            return null;
        }
        return getZeroPointOfDay(getZeroPointOfDay(new Date(millions)));
    }

    public static Date getZeroPointOfDay(Date date) {
        if (date == null) {
            return null;
        }
        DateTime datetime = DateTime.now();
        DateTime newDatetime = datetime.withMillis(date.getTime());
        return getZeroPointOfDay(newDatetime);
    }

    public static Date getZeroPointOfDay(String pattern, String date) {
        DateTime datetime = format(pattern, date);
        return getZeroPointOfDay(datetime);
    }

    public static Date getZeroPointOfDay(DateTime datetime) {
        if (datetime != null) {
            return datetime.withHourOfDay(0).withMinuteOfHour(0)
                    .withSecondOfMinute(0).withMillisOfSecond(0)
                    .toDate();
        }
        return null;
    }

    public static DateTime getZeroPointOfDaytime(DateTime datetime) {
        if (datetime != null) {
            return datetime.withHourOfDay(0).withMinuteOfHour(0)
                    .withSecondOfMinute(0).withMillisOfSecond(0);
        }
        return null;
    }

    public static Date getEndPointOfToday() {
        return getEndPointOfDay(now());
    }

    public static Date getEndPointOfDay(Date date) {
        if (date == null) {
            return null;
        }
        DateTime datetime = new DateTime();
        DateTime newDatetime = datetime.withMillis(date.getTime());
        return getEndPointOfDay(newDatetime);
    }

    public static Date getEndPointOfDay(String pattern, String date) {
        DateTime datetime = format(pattern, date);
        return getEndPointOfDay(datetime);
    }

    public static Date getEndPointOfDay(Long millions) {
        if (millions == null) {
            return null;
        }
        return getEndPointOfDay(getZeroPointOfDay(new Date(millions)));
    }

    public static Date getEndPointOfDay(DateTime datetime) {
        if (datetime != null) {
            return datetime.withHourOfDay(23).withMinuteOfHour(59)
                    .withSecondOfMinute(59).withMillisOfSecond(999)
                    .toDate();
        }
        return null;
    }

    public static DateTime getEndPointOfDaytime(DateTime datetime) {
        if (datetime != null) {
            return datetime.withHourOfDay(23).withMinuteOfHour(59)
                    .withSecondOfMinute(59).withMillisOfSecond(999);
        }
        return null;
    }

    public static String format2String(Date date, String pattern) {
        if (date == null || StringUtils.isEmpty(pattern)) {
            return null;
        }
        DateTime datetime = formatDate2DT(date);
        return format2String(datetime, pattern);
    }

    public static String format2String(DateTime time, String pattern) {
        if (time == null || StringUtils.isEmpty(pattern)) {
            return null;
        }
        return time.toString(pattern);
    }

    public static DateTime formatDate2DT(Date date) {
        if (date == null) {
            return null;
        }
        return DateTime.now().withMillis(date.getTime());
    }

    public static DateTime now() {
        return DateTime.now();
    }

    public static DateTime plusHourFromNow(int diffHours) {
        return now().plusHours(diffHours);
    }

    public static DateTime plusHour(DateTime time, int diffHours) {
        if (time == null) {
            return null;
        }
        return time.plusHours(diffHours);
    }

    public static DateTime plusSecondsOnNow(int seconds) {
        return now().plusSeconds(seconds);
    }

    public static DateTime plusSeconds(Date date, int seconds) {
        return toDateTime(date).plusSeconds(seconds);
    }

    public static boolean isBetween(DateTime time, DateTime f, DateTime t) {
        if (time == null) {
            return false;
        }
        return time.isBefore(t) && time.isAfter(f);
    }

    public static boolean isBefore(DateTime time, DateTime t) {
        if (time == null) {
            return false;
        }
        return time.isBefore(t);
    }

    public static boolean isAfter(DateTime time, DateTime t) {
        if (time == null) {
            return false;
        }
        return time.isAfter(t);
    }

    public static int daysBetween(DateTime start, DateTime end) {
        Days days = Days.daysBetween(start, end);
        return days.getDays();
    }

    public static boolean inDays(DateTime start, DateTime end, int days) {
        return daysBetween(start, end) <= days;
    }

    public static boolean beyondDays(DateTime start, DateTime end, int days) {
        return daysBetween(start, end) >= days;
    }

    public static DateTime getFirstDayOfMonth() {
        return getFirstDayOfMonth(now());
    }

    public static DateTime getFirstDayOfPrevMonth() {
        DateTime firstDay = getFirstDayOfMonth();
        return getSameTimeOfOtherMonth(firstDay, -1);
    }

    public static DateTime getFirstDayOfMonth(DateTime time) {
        return getZeroPointOfDaytime(time.withDayOfMonth(1));
    }

    // 计算最后一天，要注意计算策略，不是所有月份都有30 31号
    // 根据当前日期前推的话，如果当前日期是31，前推就会失败，所以要使用1号来前推
    public static DateTime getEndDayOfMonth(DateTime time) {
        DateTime firstDay = getFirstDayOfMonth(time);
        DateTime firstDayOfNextMonth = getSameTimeOfOtherMonth(firstDay, 1);
        DateTime endPoint4firstDay = getEndPointOfDaytime(firstDayOfNextMonth);
        return endPoint4firstDay.plusDays(-1);
    }

    public static DateTime getPrevDay(DateTime time) {
        return time.plusDays(-1);
    }

    public static DateTime getEndDayOfPrevMonth() {
        DateTime firstDayOfMonth = getFirstDayOfMonth();
        DateTime endDayOfPrevMonth = firstDayOfMonth.plusDays(-1);
        return getEndPointOfDaytime(endDayOfPrevMonth);
    }

    public static DateTime getSameDayOfPrevMonth() {
        return getSameTimeOfOtherMonth(now(), -1);
    }

    // 注意 dayOfMonth 问题
    public static DateTime getSameDayOfOtherMonth(int monthDiff) {
        return now().plusMonths(monthDiff);
    }

    public static DateTime getDayOfMonth(int day, int month) {
        DateTime now = now();
        return now.withDayOfMonth(day).withMonthOfYear(month);
    }

    public static DateTime getSameTimeOfOtherMonth(DateTime time, int monthDiff) {
        return time.plusMonths(monthDiff);
    }

    private static void print(Object obj) {
        System.out.println(obj.toString());
    }

    public static String formatDefaultDatePattern(String date) {
        DateTime dateTime = DateUtil.format(DateUtil.YYYYMMDD, date);
        return DateUtil.format2String(dateTime, DateUtil.YYYY_MM_DD);
    }

    public static String formatDatePattern(String oldPattern, String newPattern, String date) {
        DateTime dateTime = DateUtil.format(oldPattern, date);
        return DateUtil.format2String(dateTime, newPattern);
    }

    /**
     * 到date为止的年龄
     * 
     * @param birthday
     * @param date
     * @return
     */
    public static int getAge(Date birthday, Date date) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        
        int yearBirth = birthday.getYear();
        int monthBirth = birthday.getMonth();
        int dayBirth = birthday.getDate();
        
        int age = year - yearBirth;
        
        if(month < monthBirth || (month == monthBirth && day < dayBirth)) {
            age --;
        }
        return age;
    }

    public static Date addHour(Date myDate, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.HOUR, amount);
        return cal.getTime();
    }

    public static void main(String[] args) throws Exception {
//        DateTime d1 = DateUtil.format(YYYYMMDDHHMM, "201505140140");
//        String date = DateUtil.format2String(d1, DateUtil.YYYYMMDD);
//        print(date);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
////        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
//		Date dt1 = sdf.parse("2015-05-30 14:14:00");
//		Date dt2 = sdf.parse("2015-05-31 14:14:59");
//        System.out.println(dt1.getTime());
//        System.out.println(dt2.getTime());
//        System.out.println(dt1.compareTo(dt2));
//        Date dt2 = new Date();
//        dt2 = addHour(dt2, 1);
//        System.out.println(dt1.after(dt2));
        System.out.println(getAge(DateUtil.getZeroPointOfDay(YYYY_MM_DD, "2003-08-26"), DateUtil.getZeroPointOfDay(YYYY_MM_DD, "2015-08-25")));
    }

}
