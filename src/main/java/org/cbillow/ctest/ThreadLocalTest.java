package org.cbillow.ctest;

/**
 * @author Created by Cbillow
 * @date 16/3/6
 * @time 16:27
 */
public class ThreadLocalTest {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    /**
     *
     ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
         protected Long initialValue() {
            return Thread.currentThread().getId();
         };
     };
       ThreadLocal<String> stringLocal = new ThreadLocal<String>(){;
         protected String initialValue() {
            return Thread.currentThread().getName();
         };
     };
     */

    /**
     *
     * ThreadLocal一定要先set，在get，不然会报空指针异常
     * 因为如果没有set，线程第一次调用该方法，则创建并初始化此副本，此副本这个时候并没有值
     */
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();

        test.set();
        System.out.println("1:  " + test.getLong());
        System.out.println("2:  " + test.getString());

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                test.set();
                System.out.println("3:  " + test.getLong());
                System.out.println("4:  " + test.getString());
            }
        };
        thread1.start();
        thread1.join();

        System.out.println("5:  " + test.getLong());
        System.out.println("6:  " + test.getString());

    }
}
