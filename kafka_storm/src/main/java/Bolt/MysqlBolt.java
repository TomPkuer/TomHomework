package Bolt;

import java.sql.*;
import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import util.HikariConn;


public class MysqlBolt extends BaseBasicBolt {

    private static final long serialVersionUID = 5564341843792874197L;
    //private HikariService dbp;
    //private C3poProvider dbp;
    private HikariConn dbp;





    @Override
    public void prepare(Map stormConf, TopologyContext context) {

        dbp=new HikariConn();
//        try {
//            dbp=dbp.getInstance();
//            dbp.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //dbp=new C3poProvider();

        super.prepare(stormConf, context);

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
//        String word = (String) input.getValueByField("word");
//        Integer num = (Integer) input.getValueByField("count");
//        System.out.println(Thread.currentThread().getName()+"___"+input.getValue(0));
        String word =input.getString(0);
        String searchsql="select * from song_pop where word=?";
        ResultSet rs=null;
        String updateSql = "update song_pop set count =? where word=?";
        String  insertSql="insert into song_pop values(?,?)";
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn = dbp.getConnection();
            ps=conn.prepareStatement(searchsql);
            ps.setString(1,word);
            rs=ps.executeQuery();
            if (rs.next()){
                int count=rs.getInt("count");
                ps=conn.prepareStatement(updateSql);
                ps.setInt(1,(count+1));
                ps.setString(2,word);
                ps.executeUpdate();
                System.out.println(word+"  "+(count+1));


            }else {
                if (word!=null){
                ps=conn.prepareStatement(insertSql);
                ps.setString(1,word);
                ps.setInt(2,1);
                ps.executeUpdate();
                System.out.println(word+"  "+1);
                }
                else {
                    System.out.println("主键为空！");
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (ps!=null){
                        ps.close();
                    }
                    if (conn!=null){
                        conn.close();
                    }



                } catch (Exception e2) {
                    e2.printStackTrace();
                }


        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //todo 不需要定义输出的字段
    }

}
