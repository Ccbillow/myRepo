package org.cbillow.guava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

/**
 * 〈日期转换〉
 * 〈利用guava的Function，进行对象转换，将Date类型转化为string类型〉
 *
 * @author Created by chengtao
 * @date 16/7/21
 * @time 09:50
 */
public class DateFormatFunction implements Function<Date, String> {


    public static final String ymd = "yyyy-MM-dd";

    public static final String ymdhhmmss = "yyyy-MM-dd hh:mm:ss";


    public String apply(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(ymd);
        return format.format(date);
    }

    public static void main(String[] args) {
        DateFormatFunction function = new DateFormatFunction();
        Date date = new Date();
        System.out.println(function.apply(date));
    }
}
