package org.cbillow.thrift;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Created by chengtao
 * @date 16/7/7
 * @time 20:12
 */
public class AdditionServiceHandler implements AdditionService.Iface {
    @Override
    public int add(int n1, int n2) throws org.apache.thrift.TException {
        return n1 + n2;
    }
}
