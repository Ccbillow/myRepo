package org.cbillow.copybean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Created by chengtao
 * @date 16/7/21
 * @time 14:53
 */
public class TestMain {

    public static void main(String[] args) {
        FromBean fb = new FromBean();
        fb.setAddress("北京市朝阳区大屯路");
        fb.setAge(20);
        fb.setMoney(30000.111);
        fb.setIdno("110330219879208733");
        fb.setName("测试");

        IMethodCallBack apacheBU = new IMethodCallBack() {
            public String getMethodName() {
                return "BeanUtil.copyProperties";
            }

            public ToBean callMethod(FromBean fromBean) throws Exception {
                ToBean toBean = new ToBean();
                BeanUtils.copyProperties(toBean, fromBean);
                return toBean;
            }
        };

        IMethodCallBack apachePU = new IMethodCallBack() {
            public String getMethodName() {
                return "PropertyUtils.copyProperties";
            }

            public ToBean callMethod(FromBean fromBean) throws Exception {
                ToBean toBean = new ToBean();
                PropertyUtils.copyProperties(toBean, fromBean);
                return toBean;
            }
        };

        IMethodCallBack springBU = new IMethodCallBack() {
            public String getMethodName() {
                return "org.springframework.beans.BeanUtils.copyProperties";
            }

            public ToBean callMethod(FromBean fromBean) throws Exception {
                ToBean toBean = new ToBean();
                org.springframework.beans.BeanUtils.copyProperties(fromBean,
                        toBean);
                return toBean;
            }
        };


        // 数量较少的时候，测试性能
        BenchmarkTest bt = new BenchmarkTest(10);
        bt.benchmark(apacheBU, fb);
        bt.benchmark(apachePU, fb);
        bt.benchmark(springBU, fb);


        // 测试一万次性能测试
        BenchmarkTest bt10000 = new BenchmarkTest(10000);
        bt.benchmark(apacheBU, fb);
        bt.benchmark(apachePU, fb);
        bt.benchmark(springBU, fb);
    }
}
