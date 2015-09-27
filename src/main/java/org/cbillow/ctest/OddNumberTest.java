package org.cbillow.ctest;

/**
 * 判断一个数是否是基数
 */
public class OddNumberTest {

    public static void main(String[] args) {
        int i = -9;
//        int i = 3;
        System.out.println(isOdd(i));
    }

    /**
     * 判断一个数是否是基数
     */
    public static boolean isOdd(int i) {
        /**
         * 1、负数的时候永远返回false
         */
//        return i % 2 == 1;

        /**
         * 2、正确
         */
//        if (i % 2 == 1 || i % 2 == -1) {
//            return true;
//        } else {
//            return false;
//        }

        /**
         * 3、正确
         */
        return i % 2 != 0;


        /**
         * 4、"与"运算，转换为二进制，相同为1，不同为0
         */
//        return (i & 1) != 0;
    }
}
