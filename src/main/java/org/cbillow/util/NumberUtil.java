package org.cbillow.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * Created by yunl.li on 2015/4/22.
 */
public class NumberUtil {

    /**
     * string -> int,如果转化失败返回默认值
     */
    public static int getIntValue(String value, int defaultVal) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * 去掉报价中的“，”，然后转化为BigDecimal，例如“123,332.00”这种报价
     * */
    public static BigDecimal getPriceFromStr(String price) {
        return BigDecimal.valueOf(Double.valueOf(StringUtils.remove(price, ",")));
    }
}
