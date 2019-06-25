package Bolt;
import com.alibaba.fastjson.JSONObject;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;



public class SpliterBolt extends BaseBasicBolt{
    public static int count=0;

    public void execute(Tuple tuple, BasicOutputCollector collector){

        String sentence = tuple.getString(0);

        JSONObject json = new JSONObject();
        json = JSONObject.parseObject(sentence.toString());//转化成jason对象
        String message=json.getString("message");//读取kafka数据message部分内容
        String sp[]=message.split("\\|");//将数据通过|切割
        //System.out.println(sp[2]+"  "+count++);
        collector.emit(new Values(sp[2]));//数据切割后，发送歌曲编号


        }



    public void declareOutputFields(OutputFieldsDeclarer declarer){

        declarer.declare(new Fields("word"));//将数据声明为word
    }
}
