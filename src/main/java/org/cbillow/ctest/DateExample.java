package org.cbillow.ctest;

import java.util.Calendar;

/**
 * 打印本月日历
 */
public class DateExample {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, 1);
        int start = c.get(Calendar.DAY_OF_WEEK);
        int maxDay = c.getActualMaximum(Calendar.DATE);

        System.out.println("星期日 星期一 星期二 星期三 星期四 星期五 星期六");

        for (int i = 0; i < start; i++) {
            System.out.print("    ");
        }

        for (int i = 1; i <= maxDay; i++) {
            System.out.print(" " + i);
            System.out.print("   ");
            if (i < 10) {
                System.out.print(' ');
            }

            if ((start + i - 1) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
