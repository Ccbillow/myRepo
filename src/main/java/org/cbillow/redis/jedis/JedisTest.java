package org.cbillow.redis.jedis;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Created by chengtao
 * @date 16/7/7
 * @time 17:53
 */
public class JedisTest {

    private static Jedis jedis;

    @Before
    public void setUp() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    /*
     *  操作字符串
     */
    @Test
    public void testString() {
        //添加数据
        jedis.set("name", "程涛");
        System.out.println(jedis.get("name"));//获取结果
        jedis.dbSize();
        jedis.set("name1", "123");

        jedis.append("name", "123");//拼接
        System.out.println(jedis.get("name"));

        jedis.del("name");//删除某个键
        System.out.println(jedis.get("name"));

        jedis.mset("name","changsheng","age","22","qq","646653132");//设置多个键值对
        jedis.incr("age");//加1操作   在投票中可能用的上
        System.out.println(jedis.get("name")+"--"+jedis.get("age")+"--"+jedis.get("qq"));
    }

    /*
     *  操作List
     */
    @Test
    public void testList() {
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        //先向key java framework存放三条数据
        jedis.lpush("java frameword", "spring");
        jedis.lpush("java frameword", "struts");
        jedis.lpush("java frameword", "hibernate");
        //再取出所有数据
        // jedis.lrange是按范围取出
        // 第一个是key 第二个是起始位置 第三个是结束位置
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));

    }

    /*
     *  操作Set
     */
    @Test
    public void testSet() {
        jedis.sadd("haha", "why");
        jedis.sadd("haha", "you");
        jedis.sadd("haha", "so");
        jedis.sadd("haha", "diao");
        jedis.sadd("haha", "?");

        jedis.srem("haha", "?");
        System.out.println("判断？是不是haha集合的元素：" + jedis.sismember("haha", "?"));
        System.out.println("获取所有加入的value：" + jedis.smembers("haha"));
        System.out.println("返回给定集合名的一个随机的value：" + jedis.srandmember("haha"));
        System.out.println("返回集合的元素个数：" + jedis.scard("haha"));
    }

    /*
     *  操作map
     */
    @Test
    public void testMap() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("name", "小露");
        map.put("sex", "男");
        map.put("email", "64453121230@qq.com");
        jedis.hmset("user", map);

        List<String> rsmap = jedis.hmget("user", "name", "sex");
        System.out.println(rsmap);

        //删除map中某个键值
        jedis.hdel("user", "email");
        System.out.println("删除后---email" + jedis.hmget("user", "email"));
        System.out.println("是否存在key为user的记录:" + jedis.exists("user"));
        System.out.println("key为user的map中存放的值的个数:" + jedis.hlen("user"));
        System.out.println("返回map对象中所有的key:" + jedis.hkeys("user"));
        System.out.println("返回map对象中所有的value:" + jedis.hvals("user"));

        //使用迭代器
        Iterator<String> iter = jedis.hkeys("user").iterator();
        System.out.println("**********使用迭代器**********");
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    /**
     * 这里在前面执行完之后直接再去拿值   试试这些进驻内存的数据是否还在
     * 可以把服务器端关掉再重启    再直接运行这个方法看看
     * 如果还有数据就说明该数据库自动完成了持久化     它有默认的持久化机制
     */
    @Test
    public void testNoSet(){
        Iterator<String> iter = jedis.hkeys("user").iterator();
        System.out.println("***************使用迭代器***************");
        while(iter.hasNext()){
            String key = iter.next();//每次向后越过一个对象
            System.out.println(key+":"+jedis.hmget("user", key));//迭代key   根据key再取值value
        }
    }
}
