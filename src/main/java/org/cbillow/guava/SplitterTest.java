package org.cbillow.guava;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Created by chengtao
 * @date 16/7/21
 * @time 10:05
 */
public class SplitterTest {

    @Test
    public void testSplitter() {
        String str = "try ,do , your , best";
        Splitter splitter = Splitter.on(",").trimResults();
        Iterable<String> res = splitter.split(str);
        System.out.println(res);
        System.out.println(res);
        System.out.println(res);
        System.out.println(res);

    }

    @Test
    public void testMapSplitter() {
        String str = "key3=value3,key2=value2,key1=value1";
        Splitter.MapSplitter splitter = Splitter.on(",").withKeyValueSeparator("=");
        Map<String, String> res = splitter.split(str);
        System.out.println(res);
    }
}
