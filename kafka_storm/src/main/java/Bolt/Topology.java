package Bolt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;

public class Topology {
    //static Logger logger = LoggerFactory.getLogger(Topology.class);

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException {

        String topic = "test1";
        String zkRoot = "/kafka-storm";
        String id = "old";
        BrokerHosts brokerHosts = new ZkHosts("192.168.97.210:2181,192.168.96.57:2181,192.168.97.147:2181");
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, id);
        spoutConfig.forceFromStart = true;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        TopologyBuilder builder = new TopologyBuilder();
        //设置一个spout用来从kaflka消息队列中读取数据并发送给下一级的bolt组件，此处用的spout组件并非自定义的，而是storm中已经开发好的KafkaSpout
        builder.setSpout("KafkaSpout", new KafkaSpout(spoutConfig),2);//数据源
        builder.setBolt("word-spilter", new SpliterBolt(),2).shuffleGrouping("KafkaSpout");//数据切割
        //builder.setBolt("CountBolt", new CountBolt(),2).fieldsGrouping("word-spilter", new Fields("word")).setNumTasks(2);
        //builder.setBolt("MySqlBolt",new MysqlBolt(),2).shuffleGrouping("word-spilter");
        //builder.setBolt("Mysql",new MysqlBolt(),2).fieldsGrouping("word-spilter",new Fields("word"));
        //builder.setBolt("MysqlCountBolt", new MysqlCountBolt()).shuffleGrouping("writer");
        builder.setBolt("RedisBolt",new RedisBolt(),2).shuffleGrouping("word-spilter");//数据统计并持久化

        Config conf = new Config();
        conf.setNumWorkers(1);
        conf.setMaxSpoutPending(100);
        conf.setMessageTimeoutSecs(60);
        conf.setNumAckers(0);
        conf.setDebug(false);

        if (args != null && args.length > 0) {
            //提交topology到storm集群中运行
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } else {
            //LocalCluster用来将topology提交到本地模拟器运行，方便开发调试
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("WordCount", conf, builder.createTopology());
        }
    }
}


