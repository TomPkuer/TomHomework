package dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import scala.util.parsing.combinator.testing.Str;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisDao {
        private JedisPool jedisPool;
        private double set;
        private Set<String>scores;
    public void test1(){
        Jedis jedis = new Jedis("192.168.96.57");
        jedis.auth("123456");
        System.out.println("Connet to Redis-Server Successfully");
        //String  id=jedis.
        //check whether server is running or not
        // 如果返回PONG,说明正在运行
        //System.out.println(jedis.ping());

        long startTime = System.currentTimeMillis();
        //scores=jedis.zrevrange("song_pop",0,99);
        System.out.println(jedis.zscore("song_pop","10012999"));
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间


        //jedis.set("keyOne", "banana");
       //HashMap<String,String> valueOne = jedis.get("myhash");
        //String info=jedis.hget("myhash","field5");
        //double info=jedis.zscore("runoobkey","redis1");
        //jedis.zincrby("runoobkey1",1,"redis1");
        //jedis.hset("myhash","field3","hello111");
//        if (){
//
//        }

        //System.out.println("KeyOne :" + info);
       // set=//t.println(111);
        //set=jedis.zscore("song_pop","10012999");
        scores=jedis.zrange("songcount",0,0);
        //System.out.println(scores.toString());
        Iterator<String>it=scores.iterator();
        while (it.hasNext()){
            double score=jedis.zscore("songcount",it.next());
            System.out.println(score);

//            System.out.println(it.next());


        }
//        for (String s : set ){
//            System.out.println(s);
//        }
        //System.out.println(set);

    }

    public static void main(String[] args) {
        RedisDao redisDao=new RedisDao();
        redisDao.test1();
    }

}
