package com.garbage.classification.utils;

import com.garbage.classification.common.CommonCode;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: domain
 * @date: 2018/9/17
 */
@Slf4j
public class DateUtils {

    public static final String TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_FORMAT_2 = "MM-dd";

    public static final String TIME_FORMAT_3 = "yyyy/MM/dd HH:mm";

    /**
     * 获得当前时间
     *
     * @param format 时间格式
     * @return 返回String类型时间如：2018-12-01 00:00:00 格式受传入参数影响
     */
    public static String currentTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public static Date timestampToDate(int timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = new Date(timestamp);
        System.out.println(simpleDateFormat.format(data));
        return data;
    }

    /**
     * 计算工龄
     *
     * @param entryTime 入职时间
     * @return 工龄
     */
    public static int computeWorkingAge(Date entryTime) {
        Calendar entryDate = Calendar.getInstance();
        entryDate.setTime(entryTime);
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        //只要年月
        int day = (int) ((nowDate.getTimeInMillis() - entryDate.getTimeInMillis()) / (24 * 3600 * 1000));
        int year = day / 365;
        return year;
    }

    /**
     * 随机ID
     *
     * @return 时间戳+随机4位数
     */
    public static Long randomId() {
        Random random = new Random();
        int max = 9999;
        int min = 1000;
        int s = random.nextInt(max) % (max - min + 1) + min;
        Long id = System.currentTimeMillis() + s;
        return id;
    }


    /**
     * 获得当前时间
     *
     * @param format 时间格式
     * @return 返回String类型时间如：2018-12-01 00:00:00 格式受传入参数影响
     */
    public static String formatTime(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间戳(毫秒)
     *
     * @return Long 时间戳 毫米
     */
    public static Long currentTimestamp() {
        Calendar nowDate = Calendar.getInstance();
        return nowDate.getTimeInMillis();
    }

    /**
     * 格式时间
     *
     * @param value  值
     * @param format 格式类型
     * @return Date
     */
    public static Date formatDate(String value, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(value);
        } catch (Exception e) {
            date = new Date();
            log.error(CommonCode.IN_SYSTEM_ERROR, e);
        }
        return date;
    }

    /**
     * 过期时间
     *
     * @param daysLater 过期天数
     * @return 过期时间戳
     */
    public static Long expireTime(int daysLater) {
        Instant instant = Instant.now();
        instant = instant.plusMillis(TimeUnit.HOURS.toMillis(daysLater * 24));
        return instant.getEpochSecond();
    }

    /**
     * 判断是不是时间格式
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str, String pattern) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s, String pattern) throws ParseException {
        if (s == null) {
            return s;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        String res = String.valueOf(ts);
        return res;
    }
}
