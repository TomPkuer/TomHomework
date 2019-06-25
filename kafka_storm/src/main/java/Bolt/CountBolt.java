package Bolt;


import java.sql.*;
import java.util.*;

import backtype.storm.tuple.Values;
import org.apache.storm.jdbc.common.Column;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.common.HikariCPConnectionProvider;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.topology.base.BaseRichSpout;
import com.google.common.collect.Maps;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import org.apache.storm.jdbc.common.JdbcClient;


public class CountBolt extends BaseRichBolt {
    Map<String, Integer> counts = new HashMap<String, Integer>();
//    private OutputCollector collector;
//    static String driverClassName="com.mysql.jdbc.Driver";
//    static String url="jdbc:mysql://192.168.97.147:3306/test?user=root&password=666666&characterEncoding=utf-8";//后面的参数作用是打开mysq预处理功能;
//    static String username="root";
//    static String password="666666";
//    private Connection connection;
//    private PreparedStatement preparedStatement;
    private OutputCollector collector;
    private JdbcClient jdbcClient;
    private ConnectionProvider connectionProvider;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
//        Map hikariConfigMap = Maps.newHashMap();
//        hikariConfigMap.put("dataSourceClassName","com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//        hikariConfigMap.put("dataSource.url", "jdbc:mysql://192.168.97.147/test");
//        hikariConfigMap.put("dataSource.user","root");
//        hikariConfigMap.put("dataSource.password","666666");
        //connectionProvider = new HikariCPConnectionProvider(hikariConfigMap);
//        //对数据库连接进行初始化
//        connectionProvider.prepare();
//        jdbcClient = new JdbcClient(connectionProvider,30);
        //System.out.println(1111);

    }
    public void execute(Tuple tuple) {
        String word = tuple.getString(0);
        System.out.println(Thread.currentThread().getName()+"___"+tuple.getValue(0));

        //System.out.println(word);
//        Integer count = counts.get(word);
//        if(count == null)
//            count = 0;
//
//        count++;
//
//        counts.put(word,count);
//        List<Column> list = new ArrayList<Column>();
//        //创建一列将值传入   列名  值  值的类型
//        list.add(new Column("word",word, Types.VARCHAR));
//        List<List<Column>> select = jdbcClient.select("select word from song_pop_copy where word = ?",list);
//        //计算出查询的条数
//        Long n = select.stream().count();
//        if(n>=1){
//            //update
//            jdbcClient.executeSql("update song_pop_copy set count = "+count+" where word = '"+word+"'");
//        }else{
//            //insert
//            jdbcClient.executeSql("insert into song_pop_copy values( '"+word+"',"+count+")");
//        }
        //System.out.println(word +"  "+count);
        //collector.emit(new Values(word, count));






        //collector.emit()

    }

//    @Override
//    public void prepare(Map stormConf, TopologyContext context,) {
//        //System.out.println("1111");
//        //this.collector = collector;
////        try {
////            Class.forName(driverClassName);
////            connection= DriverManager.getConnection(url, username, password);
////            //preparedStatement = null;
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        //得到con连接对象
//
//        this.collector = outputCollector;
//        Map hikariConfigMap = Maps.newHashMap();
//        hikariConfigMap.put("dataSourceClassName","com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//        hikariConfigMap.put("dataSource.url", "jdbc:mysql://localhost/mc_config");
//        hikariConfigMap.put("dataSource.user","root");
//        hikariConfigMap.put("dataSource.password","admin");
//        connectionProvider = new HikariCPConnectionProvider(hikariConfigMap);
//        //对数据库连接进行初始化
//        connectionProvider.prepare();
//        jdbcClient = new JdbcClient(connectionProvider,30);
//
//
//
//
//
//    }


//    public void execute(Tuple tuple,BasicOutputCollector basicOutputCollector){
//
//        String word = tuple.getString(0);
//
//        Integer count = counts.get(word);
//        if(count == null)
//            count = 0;
//
//        count++;
//
//        counts.put(word,count);

        //collector.emit()


//        String insertSql = "replace into song_pop_copy(word,count)values(?,?)";
//
//           //for(Map.Entry<String,Integer> entry : counts.entrySet()){
//            //System.out.println(entry.getKey()+": "+entry.getValue());
//            try {
//                preparedStatement = connection.prepareStatement(insertSql);
//               preparedStatement.setString(1, word);
//                preparedStatement.setInt(2,count);
//               preparedStatement.executeUpdate();
//           } catch (Exception e) {
//               e.printStackTrace();
//           }




        //System.out.println(word +"  "+count);


  //  }


    public void declareOutputFields(OutputFieldsDeclarer declarer){

        declarer.declare(new Fields("word","count"));
    }





    @Override
    public void cleanup(){
//        try {
//            connection.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        ;

//        for (int i=0;i<10;i++){
//            System.out.println();
//        }
//        System.out.println("我被激活了！！！");
//        String insertSql = "replace into song_pop(word,count)values(?,?)";
//
//        for(Map.Entry<String,Integer> entry : counts.entrySet()){
//            //System.out.println(entry.getKey()+": "+entry.getValue());
//            try {
//                preparedStatement = connection.prepareStatement(insertSql);
//                preparedStatement.setString(1, entry.getKey());
//                preparedStatement.setInt(2,entry.getValue());
//                preparedStatement.executeUpdate();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }







