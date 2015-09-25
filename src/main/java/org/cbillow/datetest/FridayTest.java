package org.cbillow.datetest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 输出2015所有星期五
 */
public class FridayTest {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        //创建对照表
        /*String[] mons={"一月","二月","三月","四月",
                "五月","六月","七月","八月",
                "九月","十月","十一月","十二月"};
        String[] weeks = {"", "星期日", "星期一", "星期二", "星期三月", "星期四月", "星期五", "星期六"};*/
        //设置年份，日期
        c.set(2015, 0, 1);
        judgeFriday(c);

//        int year=c.get(Calendar.YEAR);
//        System.out.println(year);
//        int month=c.get(Calendar.MONTH);
//        System.out.println(mons[month]);
//        int day=c.get(Calendar.DAY_OF_MONTH);
//        System.out.println(day);
//        int week=c.get(Calendar.DAY_OF_WEEK);
//        System.out.println(weeks[week]);
    }

    /**
     * 判断星期五
     * @param c 传入已经设置好日期的Calendar对象
     */
    private static void judgeFriday(Calendar c) {
        int week = c.get(Calendar.DAY_OF_WEEK);
        int year = c.get(Calendar.YEAR);
        //跳出递归循环
        if (year == 2016) {
            return ;
        }
        //如果是星期五，则打印该日期
        if (week == 6) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            System.out.println(sdf.format(c.getTime()));
        }
        c.add(Calendar.DATE, 1);
        //递归调用本方法
        judgeFriday(c);
    }
}
