package com.example.entity;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String yyyy = "yyyy";

    /** 时间格式 HH:mm:ss */
    public static String HH_mm_ss = "HH:mm:ss";

    /** 时间格式 yyyy-MM-dd HH:mm:ss */
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式 yyyy-MM-dd */
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    /** 时间格式 yyyy-MM-dd HH:mm */
    public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public static String yyyy_MM = "yyyy-MM";


    /** 时间格式 yyyy/MM/dd HH:mm:ss */
    public static String yyyy_MM_dd_HH_mm_ss_02 = "yyyy/MM/dd HH:mm:ss";
    /** 时间格式 yyyy/MM/dd */
    public static String yyyy_MM_dd_02 = "yyyy/MM/dd";

    /** 时间格式 yyyy/MM/dd HH:mm */
    public static String yyyy_MM_dd_HH_mm_02 = "yyyy/MM/dd HH:mm";
    /** 时间格式 yyyy/MM */
    public static String yyyy_MM_02 = "yyyy/MM";

    /** 时间格式 yyyy.MM.dd HH:mm:ss */
    public static String yyyy_MM_dd_HH_mm_ss_03 = "yyyy.MM.dd HH:mm:ss";
    /** 时间格式 yyyy.MM.dd HH:mm */
    public static String yyyy_MM_dd_HH_mm_03 = "yyyy.MM.dd HH:mm";
    /** 时间格式 yyyy.MM.dd */
    public static String yyyy_MM_dd_03 = "yyyy.MM.dd";
    /** 时间格式 yyyy.MM */
    public static String yyyy_MM_03 = "yyyy.MM";
    /** 时间格式 yyyyMMddHHmmss */
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    /** 时间格式 yyyyMMddHHmmssSSS */
    public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    /** 时间格式 yyyyMMddHHmm */
    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private static String[] parsePatterns = {
            yyyy_MM_dd_HH_mm_ss,
            yyyy_MM_dd_HH_mm,
            yyyy_MM_dd,
            yyyy_MM,
            yyyy_MM_dd_HH_mm_ss_02,
            yyyy_MM_dd_HH_mm_02,
            yyyy_MM_dd_02,
            yyyy_MM_02,
            yyyy_MM_dd_HH_mm_ss_03,
            yyyy_MM_dd_HH_mm_03,
            yyyy_MM_dd_03,
            yyyy_MM_03,
            yyyyMMddHHmmss,
            yyyyMMddHHmmssSSS
    };

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }
    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    /**
     * 获取当前Date型日期
     * @return Date() 当前日期
     */
    public static Date nowDate() {
        return new Date();
    }

    /**
     * 获取当前时间的时间戳 （单位：毫秒）
     * @return 返回当前时间的时间戳 （单位：毫秒）
     */
    public static Long nowTs()
    {
        return new Date().getTime();
    }

    /**
     * 返回当前字符串时间，例如返回：2022-10-18 12:00:00
     * @return
     */
    public static String nowDateStr()
    {
        return dateToStr(new Date());
    }

    /**
     * 返回指定格式的字符串时间
     * 例如：
     *   DateUtils.nowDateStr(DateUtils.yyyy_MM_dd_HH_mm_ss_02);  返回：2022/10/19 11:28:19
     *   DateUtils.nowDateStr(DateUtils.yyyyMMddHHmmssSSS);  返回：20221019112433217
     * @return
     */
    public static String nowDateStr(String format) {
        return dateToStr(new Date(), format);
    }

    /**
     * 获取当周的开始时间
     * 现在是2022-10-18 12:00:11，tsGetWeekStart()返回 2022-10-17 00:00:00(周一的0点) 的时间戳
     * @return 返回时间戳（单位：毫秒）
     */
    public static Long tsGetWeekStart() {
        return dateGetWeekStart().getTime();
    }
    public static Long tsGetWeekStart(Long ts) {
        return dateGetWeekStart(new Date(ts)).getTime();
    }
    public static Long tsGetWeekStart(Date date) {
        return dateGetWeekStart(date).getTime();
    }

    /**
     * 获取当月的开始时间
     * 例如：
     *     现在是2022-10-18 12:00:11，tsGetMonthStart()返回 2022-10-01 00:00:00 的时间戳
     * @return 返回时间戳（单位：毫秒）
     */
    public static Long tsGetMonthStart() {
        return dateGetMonthStart().getTime();
    }
    public static Long tsGetMonthStart(Long ts) {
        return dateGetMonthStart(new Date(ts)).getTime();
    }
    public static Long tsGetMonthStart(Date date) {
        return dateGetMonthStart(date).getTime();
    }

    /**
     * 将 时间戳 转 字符串时间
     * @param timestamp 单位：毫秒
     * @return
     */
    public static final String tsToStr(Long timestamp)
    {
        return tsToStr(timestamp, DateUtils.yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将 时间戳 转 字符串时间
     * @param timestamp 单位：毫秒
     * @param format
     * @return
     */
    public static final String tsToStr(Long timestamp, String format)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(timestamp);
    }

    /**
     * 将 时间戳 转 java.util.Date
     * @param timestamp 单位：毫秒
     * @return 日期类型
     */
    public static final Date tsToDate(Long timestamp)
    {
        return new Date(timestamp);
    }

    /**
     * 操作时间戳
     * 例如： 在当前时间戳的基础上新增3天 tsOp(new Date().getTime(), Calendar.DAY_OF_MONTH, day)
     * @param timestamp
     * @param calendarType 例如：值为 Calendar.DAY_OF_MONTH
     * @param num  num>0 增加  num<0 减少
     * @return
     */
    public static long tsOp(long timestamp, int calendarType, int num) {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date(timestamp)); //设置参数时间
        c.add(calendarType, num);
        return c.getTime().getTime();
    }

    /**
     * 操作当前时间戳，在当前时间戳的基础上增加或减少的天数，返回新的时间戳
     * @param timestamp  时间戳（单位：毫秒）
     * @param day day>0 增加多少天  day<0 减少多少天
     * @return
     */
    public static long tsOpDay(long timestamp, int day) {
        return tsOp(timestamp, Calendar.DAY_OF_MONTH, day);
    }

    /**
     * 操作当前时间戳，在当前时间戳的基础上增加或减少的小时数，返回新的时间戳
     * @param timestamp  时间戳（单位：毫秒）
     * @param hour hour>0 增加多少小时  hour<0 减少多少小时
     * @return
     */
    public static long tsOpHour(long timestamp, int hour) {
        return tsOp(timestamp, Calendar.HOUR_OF_DAY, hour);
    }

    /**
     * 操作当前时间戳，在当前时间戳的基础上增加或减少的分钟数，返回新的时间戳
     * @param timestamp  时间戳（单位：毫秒）
     * @param minute minute>0 增加多少分钟  minute<0 减少多少分钟
     * @return
     */
    public static long tsOpMinute(long timestamp, int minute) {
        return tsOp(timestamp, Calendar.MINUTE, minute);
    }

    /**
     * 操作当前时间戳，在当前时间戳的基础上增加或减少的秒数，返回新的时间戳
     * @param timestamp  时间戳（单位：毫秒）
     * @param second second>0 增加多少秒  second<0 减少多少秒
     * @return
     */
    public static long tsOpSecond(long timestamp, int second) {
        return tsOp(timestamp, Calendar.SECOND, second);
    }

    /**
     * 获取当周的开始时间
     * 现在是2022-10-18 12:00:11，dateOfNowWeekStart()返回 'Mon Oct 17 00:00:00 GST 2022'（2022-10-17 00:00:00）(周一的0点)
     * @return
     */
    public static Date dateGetWeekStart() {
        return dateGetWeekStart(new Date());
    }

    /**
     * 根据日期，获取日期当周的开始时间
     * 现在是2022-10-18 12:00:11，dateGetWeekStart(new Date())返回 'Mon Oct 17 00:00:00 GST 2022'（2022-10-17 00:00:00）(周一的0点)
     * @param date
     * @return
     */
    public static Date dateGetWeekStart(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当月的开始时间
     * 例如：
     *     现在是2022-10-18 12:00:11，dateGetMonthStart()返回 'Sat Oct 01 00:00:00 GST 2022'（2022-10-01 00:00:00）
     * @return
     */
    public static Date dateGetMonthStart() {
        return dateGetMonthStart(new Date());
    }

    /**
     * 根据日期，获取日期当月的开始时间
     * @param date
     * @return
     */
    public static Date dateGetMonthStart(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 将java.util.Date 格式化，默认格式化为 YYYY_MM_DD_HH_MM_SS
     * @param date
     * @return 返回格式化后的字符串
     */
    public static final String dateToStr(final Date date)
    {
        return dateToStr(date, DateUtils.yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将java.util.Date 格式化
     * 备注：将一个现有的date(new Date除外)转化成带有纳秒的字符串时，会丢失纳秒数据。例如：DateUtils.dateToStr(date, DateUtils.yyyyMMddHHmmssSSS); date参数字符串的值为：20221019111927594 结果：20221019185854000
     * @param format
     * @param date
     * @return 返回格式化后的字符串
     */
    public static final String dateToStr(Date date, String format)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期类型转化为时间戳
     * @param date
     * @return  时间戳 （单位:毫秒）
     */
    public static long dateToTs(Date date) {
        return date.getTime();
    }

    /**
     * Date日期 操作
     * 例如： 在当前时间的基础上新增1天 DateUtils.dateOp(new Date(), Calendar.DAY_OF_MONTH, 1)  返回: Thu Oct 20 13:43:26 GST 2022
     * @param date
     * @param calendarType 例如：值为 Calendar.DAY_OF_MONTH
     * @param num  num>0 增加  num<0 减少
     * @return
     */
    public static Date dateOp(Date date, int calendarType, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date); //设置参数时间
        c.add(calendarType, num);
        return c.getTime();
    }

    /**
     * Date日期 操作
     * 例如： 在当前时间的基础上新增1天 DateUtils.dateOpDay(new Date(), 1)  返回: Thu Oct 20 13:43:26 GST 2022
     * @param date
     * @param day  day>0 增加多少天  day<0 减少多少天
     * @return
     */
    public static Date dateOpDay(Date date, int day) {
        return dateOp(date, Calendar.DAY_OF_MONTH, day);
    }

    /**
     * Date日期 操作
     * 例如： 在当前时间的基础上新增1小时 DateUtils.dateOpHour(new Date(), 1)  返回: Thu Oct 20 13:43:26 GST 2022
     * @param date
     * @param hour  hour>0 增加多少小时  hour<0 减少多少小时
     * @return
     */
    public static Date dateOpHour(Date date, int hour) {
        return dateOp(date, Calendar.HOUR_OF_DAY, hour);
    }

    /**
     * Date日期 操作
     * 例如： 在当前时间的基础上新增10分钟 DateUtils.dateOpMinute(new Date(), 10)  返回: Thu Oct 20 13:43:26 GST 2022
     * @param date
     * @param minute minute>0 增加多少分钟  minute<0 减少多少分钟
     * @return
     */
    public static Date dateOpMinute(Date date, int minute) {
        return dateOp(date, Calendar.MINUTE, minute);
    }

    /**
     * Date日期 操作
     * 例如： 在当前时间的基础上新增15秒 DateUtils.dateOpSecond(new Date(), 15)  返回: Thu Oct 20 13:43:26 GST 2022
     * @param date
     * @param second second>0 增加多少秒  second<0 减少多少秒
     * @return
     */
    public static Date dateOpSecond(Date date, int second) {
        return dateOp(date, Calendar.SECOND, second);
    }

    /**
     * 日期是否能被整除
     * 例如：
     *   当前日期是否是5分钟的倍数 dateIsAliquot(new Date(), "minute", 5)
     *   当前日期是否是1小时的倍数 dateIsAliquot(new Date(), "hour", 1)
     * @param date 日期
     * @param type 类型
     * @param num 类型对应的时间
     * @return
     */
    public static boolean dateIsAliquot(Date date, String type, int num) {
        if (date != null && num > 0 ) {
            // 整月、整天、整时、整分钟、整秒
            if (type.equals("minute")) { // 整分钟
                return date.getTime() % (num * 60 * 1000) == 0;
            }
            if (type.equals("hour")) { // 整时
                return date.getTime() % (num * 60 * 60 * 1000) == 0;
            }
//            if (type.equals("day")) { // 整天 ， 不能这么判断
//                return date.getTime() % (num * 24 * 60 * 60 * 1000) == 0;
//            }
        }
        return false;
    }

    /**
     * 日期是否能被 minute分钟 整除
     * 例如：
     *    当前日期是否是5分钟的倍数 dateIsAliquotByMinute(new Date(), 5)
     * @param date 日期
     * @param minute 分钟
     * @return
     */
    public static boolean dateIsAliquotByMinute(Date date, int minute){
        return dateIsAliquot(date, "minute", minute);
    }

    /**
     * 日期是否能被 num小时 整除
     * 例如：
     *    当前日期是否是1小时的倍数 dateIsAliquotByHour(new Date(), 1)
     * 实例：
     *    Date date = DateUtils.strToDate("2022-10-18 16:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS);
     *    boolean flag = DateUtils.dateIsAliquotByHour(date, 2); // true
     * @param date 日期
     * @param hour 小时
     * @return
     */
    public static boolean dateIsAliquotByHour(Date date, int hour){
        return dateIsAliquot(date, "hour", hour);
    }

    /**
     * 判断日期是否是整日
     * @param date 日期
     * @return
     */
    public static boolean dateIsAliquotDay(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return date.getTime() == calendar.getTimeInMillis();
        }
        return false;
    }

    /**
     * 判断当前时间是当周开始时间
     *
     * @param date
     * @return
     */
    public static boolean dateIsWeekStart(Date date) {
        if (date != null) {
            return date.getTime() == dateGetWeekStart().getTime();
        }
        return false;
    }

    /**
     * 判断当前时间是当月开始时间
     *
     * @param date
     * @return
     */
    public static boolean dateIsMonthStart(Date date) {
        if (date != null) {
            return date.getTime() == dateGetMonthStart().getTime();
        }
        return false;
    }

    /**
     * 判断一个日期是否在 开始日期 和 结束日期 之间
     * @param date 当前日期
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static Boolean dateIsInBetweenDate(Date date, Date startDate, Date endDate) {
        Boolean result = false;
        if (date.after(startDate) && date.before(endDate)) {
            result = true;
        }
        return result;
    }

    /**
     * 比较两个时间是否是同一天
     *
     * @param date0
     * @param date1
     * @return
     */
    public static boolean dateIsSameDay(Date date0, Date date1) {
        if (date0 == null || date1 == null) {
            return false;
        }
        String str0 = dateToStr(date0, yyyy_MM_dd);
        String str1 = dateToStr(date1, yyyy_MM_dd);
        return str0.equals(str1);
    }

    /**
     * 判断传入时间是否超过24小时
     * @param date
     * @return
     */
    public static boolean dateIsMoreThanHours(Date date) {
        return dateIsMoreThanHours(date, 24);
    }

    /**
     * 判断传入时间是否超过多少小时
     * @param date
     * @param hour
     * @return
     */
    public static boolean dateIsMoreThanHours(Date date, int hour) {
        return date.getTime() > new Date().getTime() - 1 * hour * 60 * 60 * 1000;
    }

    /**
     * 判断某天是否为周几
     * @param date 某天
     * @param weekday  值为：Calendar.MONDAY、Calendar.THURSDAY
     * @return
     */
    public static boolean dateIsWeekDay(Date date, int weekday) {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        if (date != null)
            calendar.setTime(date);
        int today = calendar.get(calendar.DAY_OF_WEEK);
        if (weekday == today) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断今天是否为周几
     * 例如：
     *   判断今天是否是周一 dateIsWeekDay(Calendar.MONDAY)
     *   判断今天是否是周二 dateIsWeekDay(Calendar.THURSDAY)
     * @param weekday  值为：Calendar.MONDAY、Calendar.THURSDAY
     * @return
     */
    public static boolean dateIsWeekDay(int weekday) {
        return dateIsWeekDay(null, weekday);
    }

    /**
     * 判断是否是周末
     */
    public static boolean dateIsWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int today = cal.get(Calendar.DAY_OF_WEEK);
        if (today == Calendar.SATURDAY || today == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断某天是否为某月第一天
     * @param date 某天
     * @return
     */
    public static boolean dateIsFirstDayOfMonth(Date date) {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        if (date != null)
            calendar.setTime(date);
        int today = calendar.get(calendar.DAY_OF_MONTH);
        if (1 == today) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断今天是否为本月第一天
     * @return
     */
    public static boolean dateIsFirstDayOfMonth() {
        return dateIsFirstDayOfMonth(null);
    }

    /**
     * 日期型字符串转化为日期 格式
     * 例如：
     *     DateUtils.strToDate("2022-11-18 12:00:11");
     *     DateUtils.strToDate("2022/11/18 12:00:11");
     *     DateUtils.strToDate("2022.11.18 12:00:11");
     *     DateUtils.strToDate("20221118120011");
     * @param strDate 自动匹配时间格式
     * @return
     */
    public static Date strToDate(String strDate) {
        if (strDate != null) {
            try {
                return parseDate(strDate, parsePatterns);
            } catch (ParseException e) { }
        }
        return null;
    }

    /**
     * string类型 转换为 java.util.Date类型
     * 例如：
     *   StrToDate("2022-10-18 13:23:56", "yyyy-MM-dd HH:mm:ss");  转换为一个 java.util.Date
     * @param strDate
     * @param format 指定格式
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            date = formatter.parse(strDate);
        } catch ( Exception e) {
            log.error("日期转换错误");
        }
        return date;
    }

    /**
     * 将 字符串时间 转化 时间戳
     * 备注：strDate的时间格式和format的时间格式必须相同
     * @param strDate 例如：2022-10-18 13:23:56
     * @param format 例如：yyyy-MM-dd HH:mm:ss
     * @return 时间戳（单位：毫秒）
     */
    public static long strToTs(String strDate, String format) {
        Date date = strToDate(strDate, format); // String类型转成date类型
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    /**
     * 获取 日期 某天的结束时间
     * 例如：
     *   获取今天的结束时间 DateUtils.getDateEndTime(new Date(), 0)
     *   获取明天的结束时间 DateUtils.getDateEndTime(new Date(), 1)
     *   获取昨天的结束时间 DateUtils.getDateEndTime(new Date(), -1)
     * @param date
     * @return
     */
    public static Date getDateEndTime(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0); // 此处如果设置成23：59：59：999 mysql 会自会把毫秒四舍五入变成下一天0:00:00
        if (num != 0 )
            c.add(Calendar.DAY_OF_MONTH, num);
        return c.getTime();
    }

    /**
     * 获取 日期 当天的结束时间
     * @param date
     * @return
     */
    public static Date getDateTodayEndTime(Date date) {
        return getDateEndTime(date, 0);
    }

    /**
     * 获取 当前时间 下一天的开始时间
     * @param date
     * @return
     */
    public static Date getDateTomorrowEndTime(Date date) {
        return getDateEndTime(date, 1);
    }

    /**
     * 获取 当前时间 上一天的开始时间
     * @param date
     * @return
     */
    public static Date getDateYesterdayEndTime(Date date) {
        return getDateEndTime(date, -1);
    }

    /**
     * 获取 日期 某天的开始时间
     * 例如：
     *   获取今天的开始时间 DateUtils.getDateStartTime(new Date(), 0)
     *   获取明天的开始时间 DateUtils.getDateStartTime(new Date(), 1)
     *   获取昨天的开始时间 DateUtils.getDateStartTime(new Date(), -1)
     * @param date
     * @param day day>0增加一天, day<0减少一天
     * @return
     */
    public static Date getDateStartTime(Date date, int day) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        if (day != 0 )
            c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
     * 获取 日期 当天的开始时间
     * @param date
     * @return
     */
    public static Date getDateTodayStartTime(Date date) {
        return getDateStartTime(date, 0);
    }

    /**
     * 获取 当前时间 下一天的开始时间
     * @param date
     * @return
     */
    public static Date getDateTomorrowStartTime(Date date) {
        return getDateStartTime(date, 1);
    }

    /**
     * 获取 当前时间 上一天的开始时间
     * @param date
     * @return
     */
    public static Date getDateYesterdayStartTime(Date date) {
        return getDateStartTime(date, -1);
    }

    /**
     * 获取当前月天数
     * 例如，这个月31天，返回31
     * @return
     */
    public static int getDaysForMonth() {
        return getDaysForMonth(new Date());
    }

    /**
     * 根据日期，获取这个日期的这个月的天数
     * 例如，这个月31天，返回31
     * @param date
     * @return
     */
    public static int getDaysForMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取 这个时间 这个月最后一天
     * @param date
     * @return
     */
    public static Date getEndDayFromDateMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取 这个时间 这个月最后一天 的开始时间
     * @param date
     * @return
     */
    public static Date getEndDayStartFromDateMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取 这个时间 这个月最后一天 的结束时间
     * @param date
     * @return
     */
    public static Date getEndDayEndFromDateMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 通过秒获取分钟
     * @param second
     * @return
     */
    public static long getSencondFromMinute(long second) {
        if ( second != 0) {
            return TimeUnit.SECONDS.toMinutes(second);
        }
        return 0;
    }

    /**
     * 获取今天过了多少小时
     * @return 时间当天的小时数 24小时制
     * 比如 2018-08-08 18:08:08 返回 18
     * 比如 2018-08-08 06:08:08 pm 返回 18
     */
    public static Integer getDateHour() {
        return getDateHour(new Date());
    }

    /**
     * 获取时间 的小时数
     * @param date 时间
     * @return 时间当天的小时数 24小时制
     * 比如 2018-08-08 18:08:08 返回 18
     * 比如 2018-08-08 06:08:08 pm 返回 18
     */
    public static Integer getDateHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null )
            calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取这周星期几
     * @return
     */
    public static Integer getDateWeek() {
        return getDateWeek(new Date());
    }

    /**
     * 根据时间获取星期几
     * @param date
     * @return
     */
    public static Integer getDateWeek(Date date) {
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取JAVA服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算 开始日期 到 结束日期 之间相差的 秒数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static long diffSecondByDate(Date startDate, Date endDate) {
        long secondNum = 0;
        long startTimeStamp = startDate.getTime();
        long endTimeStamp = endDate.getTime();
        secondNum = (endTimeStamp - startTimeStamp) / 1000;
        return secondNum;
    }

    /**
     * 计算 开始日期 到 结束日期 之间相差的 分钟数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static long diffMinuteByDate(Date startDate, Date endDate) {
        long minute;
        long dift = diffSecondByDate(startDate, endDate);
        minute = dift / 60;
        return minute;
    }

    /**
     * 计算 开始日期 到 结束日期 之间相差的 天数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static long diffDayByDate(Date startDate, Date endDate)
    {
        long diff = ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
        return diff;
    }

    /**
     * 计算 开始时间戳 到 结束时间戳 之间相差的 天数
     * @param start 开始时间戳
     * @param end 结束时间戳
     * @return 天数
     */
    public static int diffDayByTs(long start, long end) {
        if (start > end) {
            return 0;
        } else {
            long interval = end - start;
            return (int) interval / (60 * 60 * 24);
        }
    }

    /**
     * 计算 开始日期 到 结束日期 之间相差的 月数数组
     * 例如：
     *   DateUtils.diffMonthByStrDate("2019-06", "2019-12") 返回：[2019-06, 2019-07, 2019-08, 2019-09, 2019-10, 2019-11, 2019-12]
     * @param startDate 开始日期 格式: 2019-09
     * @param endDate 结束日期 格式: 2019-12
     * @return
     */
    public static List<String> diffMonthStrListBetweenDateStr(String startDate, String endDate) {
        String[] startArr = startDate.split("-");
        String[] endArr = endDate.split("-");

        int months = (Integer.parseInt(endArr[0]) - Integer.parseInt(startArr[0])) * 12 + (Integer.parseInt(endArr[1]) - Integer.parseInt(startArr[1]));

        List<String> monthList = new ArrayList<>();
        monthList.add(startDate);

        int year = Integer.parseInt(startArr[0]);
        int month = Integer.parseInt(startArr[1]);

        for (int i = 0; i < months; i++) {
            month = month + 1;

            if (month > 12) {
                year++;
                month = 1;
            }

            monthList.add(year + "-" + (month <= 9 ? "0" + month : month));
        }

        return monthList;
    }

    /**
     * 根据 开始时间 和 结束时间 返回 时间段内的时间集合
     * @param startDate
     * @param endDate
     * @return List<Date>
     */
    public static List<String> diffDateListBetweenDate(Date startDate, Date endDate) {
        List<String> lDate = new ArrayList<>();
        if (startDate.after(endDate)){
            return lDate;
        }

        lDate.add(dateToStr(startDate,yyyy_MM_dd));// 把开始时间加入集合
        if (dateToStr(startDate,yyyy_MM_dd).equals(dateToStr(endDate))){
            return lDate;
        }
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(startDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后

            if (endDate.after(cal.getTime()) || endDate.getTime()==cal.getTime().getTime()) {
                lDate.add(dateToStr(cal.getTime(),yyyy_MM_dd));
            } else {
                break;
            }
        }
//        lDate.add(dateToStr(endDate,yyyy_MM_dd));// 把结束时间加入集合
        return lDate;
    }

    /**
     * 根据 开始时间 和 结束时间 返回 时间段内的时间集合倒序
     * @param startDate
     * @param endDate
     * @return List<Date>
     */
    public static List<String> diffDateListBetweenDateDesc(Date startDate, Date endDate) {
        List<String> lDate = new ArrayList<>();
        if (startDate.after(endDate)){
            return lDate;
        }

        lDate.add(dateToStr(startDate,yyyy_MM_dd));// 把开始时间加入集合
        if (dateToStr(startDate,yyyy_MM_dd).equals(dateToStr(endDate))){
            return lDate;
        }
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(startDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后

            if (endDate.after(cal.getTime()) || endDate.getTime()==cal.getTime().getTime()) {
                lDate.add(dateToStr(cal.getTime(),yyyy_MM_dd));
            } else {
                break;
            }
        }
        Collections.sort(lDate, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        return lDate;
    }

    /**
     * 计算两个时间差  返回时间格式如：5天10小时20分
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static String diffStrByDate(Date startDate, Date endDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 将 java.time.LocalDateTime 转 java.util.Date
     * @param localDateTime LocalDateTime
     * @return java.util.Date
     */
    public static Date ldsToDate(LocalDateTime localDateTime)
    {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 java.time.LocalDate 转 java.util.Date
     * @param localDate LocalDate
     * @return java.util.Date
     */
    public static Date ldsToDate(LocalDate localDate)
    {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /*计算两个日期之间的天数,不满一天按照一天算*/
    public static Long getDayNumBetweenDate(Date startDate, Date endDate) {
        SimpleDateFormat f = new SimpleDateFormat(yyyy_MM_dd);

        String startDateStr = f.format(startDate);
        String endDateStr = f.format(endDate);

        long dayNum = 0;
        try {
            Date start = f.parse(startDateStr);
            Date end = f.parse(endDateStr);

            long startTimeStamp = start.getTime();
            long endTimeStamp = end.getTime();

            dayNum = (endTimeStamp - startTimeStamp) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayNum;
    }

    /**
     * 补全2个日期之间的日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getDatesBetweenTwoDate(String beginDate, String endDate) {
        DateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd);
        List<String> dateList = new ArrayList<>();

        try {
            Date startDate = dateFormat.parse(beginDate);
            Date finishDate = dateFormat.parse(endDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);

            Long days = getDayNumBetweenDate(startDate, finishDate);

            dateList.add(beginDate);

            for (int i = 0; i < days; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);

                dateList.add(dateFormat.format(cal.getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateList;
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor)
    {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor)
    {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 相差几个月
     * @param startDate
     * @param endDate
     * @return
     */

    public static int calculateMonthDifference(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int endYear = endCalendar.get(Calendar.YEAR);
        int endMonth = endCalendar.get(Calendar.MONTH);

        return (endYear - startYear) * 12 + (endMonth - startMonth);
    }

    /**
     * 获取一周前的日期
     */
    public static Date getLastWeekDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 根据日期，获取日期当周的结束时间
     * @param date
     * @return
     */
    public static Date dateGetWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);

        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.WEEK_OF_YEAR,1);
        return cal.getTime();
    }



    public static Date getStartDayMonth() {
        LocalDate now = LocalDate.now();
        LocalDateTime todayStart = now.atStartOfDay();
        LocalDateTime with = todayStart.with(TemporalAdjusters.firstDayOfMonth());
        return toDate(with);
    }


    public static Date getEndDayMonth() {
        LocalDate now = LocalDate.now();
        LocalDateTime todayStart = LocalDateTime.of(now, LocalTime.MAX);
        ;
        LocalDateTime with = todayStart.with(TemporalAdjusters.lastDayOfMonth());
        return toDate(with);
    }


    public static String getNowYear() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        String format = LocalDate.now().format(dateTimeFormatter);
        return format;
    }


    /**
     * 获取 指定时间得00:00:00
     * @param date
     * @return
     */
    public static Date getStartOfDay (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }


    /**
     * 获取 指定时间得23:59:59
     * @param date
     * @return
     */
    public static Date getEndOfDay (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

}
