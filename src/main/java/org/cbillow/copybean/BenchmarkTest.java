package org.cbillow.copybean;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Created by chengtao
 * @date 16/7/21
 * @time 14:47
 */
public class BenchmarkTest {

    private int count;

    public BenchmarkTest(int count) {
        this.count = count;
        System.out.println("性能测试" + this.count + "=========");
    }

    public void benchmark(IMethodCallBack m, FromBean fromBean) {
        try {
            long begin = new Date().getTime();
            ToBean toBean = null;
            System.out.println(m.getMethodName() + "开始测试");
            for (int i = 0; i < count; i++) {
                toBean = m.callMethod(fromBean);
            }

            long end = new Date().getTime();
            System.out.println(m.getMethodName() + "耗时" + (end - begin));
//            System.out.println(toBean.getAddress());
//            System.out.println(toBean.getAge());
//            System.out.println(toBean.getIdno());
//            System.out.println(toBean.getMoney());
//            System.out.println(toBean.getName());
            System.out.println("结束测试");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
