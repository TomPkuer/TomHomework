package Bolt;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RedisBolt extends BaseBasicBolt {
    private static final long serialVersionUID = 3600226627626777952L;
    private JedisPool jedisPool;
    private Jedis jedis;
    //private String id;
    private int score; //当前统计次数
//    private Set<String> ids;
//double zco;

    // private String
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        jedisPool = new JedisPool("192.168.96.57");
        jedis = jedisPool.getResource();
        jedis.auth("123456");   //redis密码验证
        super.prepare(stormConf, context);

    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String word = tuple.getString(0);
        System.out.println(word);

        String count = jedis.hget("runoobkey", word);//从数据库中获取当前id的计数
        if (count == null) {//值为空，则计数为1
            jedis.hset("runoobkey", word, "1");
            //id=word;
            score = 1;

        } else {
            int newcount = Integer.parseInt(count) + 1;
            jedis.hset("runoobkey", word, String.valueOf(newcount));
            score = newcount;
        }
        Long num=jedis.zcard("songcount");//获得当前表的长度
        //jedis.hget("runoobkey","word");

        if (num <= 100) { //表的大小控制在100
            jedis.zadd("songcount", score, word); //新计算的数据插入
        }

        else{
            jedis.zadd("songcount", score, word);
            jedis.zremrangeByRank("songcount",0,num-101);//删除排名100之后的数据
        }

            }


//            ids = jedis.zrange("songcount", 0, 0);
////            Iterator<String> iterator = ids.iterator();
////            //System.out.println(jedis.zscore("songcount",iterator.next()));
////            while (iterator.hasNext()) {
////                String prescore=iterator.next();
////                //System.out.println(jedis.zscore("songcount", prescore));
////
////             try {
////
////                    zco=jedis.zscore("songcount", prescore);
////                    if (score >=zco) { //取出当前表中最小值进行比较
////                     jedis.zremrangeByRank("songcount", 0, 0); //当表大小超出100，从小到大排序，删除序列最小值
////                     jedis.zadd("songcount", score, word);
////
////                 }
////             }catch (Exception e){
////                 e.printStackTrace();
////             }
////
////            }





//        System.out.println(word);
        //jedis.zincrby("song_pop",1,word); //数据增量设置为1，每当有相同词，score值加一。

        //jedis.hset("mybash",word,);

    //}

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
