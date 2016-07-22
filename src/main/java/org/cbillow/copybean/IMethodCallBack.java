package org.cbillow.copybean;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Created by chengtao
 * @date 16/7/21
 * @time 14:49
 */
public interface IMethodCallBack {

    String getMethodName();

    ToBean callMethod(FromBean fromBean) throws Exception;
}
