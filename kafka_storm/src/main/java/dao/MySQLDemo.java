package dao;

import com.alibaba.fastjson.JSONObject;
import util.C3poProvider;
import util.DruidProvider;
import util.HikariConn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class MySQLDemo {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.97.147:3306/test?user=root&password=666666&characterEncoding=utf-8";

    // 数据库的用户名与密码，需要根据自己的设置
//    static final String USER = "root";
//    static final String PASS = "1234";

//    public void update(String id ,int clickTime) {
//
//        String searchSql = "select * from song_pop where key = ?";
//        String insertSql = "insert into song_pop(key,value)values(?,?)";
//        String updateSql = "UPDATE song_pop  SET value = ? WHERE key = ?";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = DriverManager.getConnection(DB_URL,USER,PASS);
//            preparedStatement = connection.prepareStatement(searchSql);
//            preparedStatement.setString(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs == null) {
//                preparedStatement = connection.prepareStatement(insertSql);
//                preparedStatement.setString(1, id);
//                preparedStatement.setInt(2, clickTime);
//                preparedStatement.executeUpdate();
//            } else {
//                preparedStatement = connection.prepareStatement(updateSql);
//                preparedStatement.setInt(1, clickTime);
//                preparedStatement.setString(2, id);
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//    }



//    public static void main(String[] args) {
//        Connection conn = null;
//        PreparedStatement preparedStatement=null;
//        Statement stmt = null;
//        try{
//            // 注册 JDBC 驱动
//            Class.forName("com.mysql.jdbc.Driver");
//
//            // 打开链接
//            System.out.println("连接数据库...");
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//            // 执行查询
//            System.out.println(" 实例化Statement对象...");
//
//
//             //stmt = conn.createStatement();
//
//            String sql;
//            sql = "insert into song_pop(key,value)values(?,?)";
//            preparedStatement=conn.prepareStatement(sql);
//            preparedStatement.setString(1,"hello");
//            preparedStatement.setInt(2,111);
//            preparedStatement.executeUpdate();
//
//            //ResultSet rs = stmt.executeQuery(sql);
//
//
//            // 展开结果集数据库
////            while(rs.next()){
////                // 通过字段检索
////                int id  = rs.getInt("value");
////                String name = rs.getString("key");
////                //String url = rs.getString("url");
////
////                // 输出数据
////                System.out.print("ID: " + id);
////                System.out.print(", 站点名称: " + name);
////                //System.out.print(", 站点 URL: " + url);
////                System.out.print("\n");
////            }
//            // 完成后关闭
//            //rs.close();
//            preparedStatement.close();
//            conn.close();
//        }catch(SQLException se){
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        }catch(Exception e){
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        }finally{
//            // 关闭资源
//            try{
//                if(stmt!=null) stmt.close();
//            }catch(SQLException se2){
//            }// 什么都不做
//            try{
//                if(conn!=null) conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Goodbye!");
////        MySQLDemo mySQLDemo=new MySQLDemo();
////        String time="hello";
////        mySQLDemo.update(time,11);
//   }

    public static void readFromFile() throws IOException, SQLException {
        //String path ="C:\\Users\\w5275\\Desktop\\hdfslogs\\hdfs.2019-06-06.0.log";
//        BufferedReader br = new BufferedReader(
//                new FileReader(path));
//        String line = null;
        String search="select distinct customerid from rankbyboth2";
        String gettop="select * from rankbyboth2 where customerid=? order by rank limit 1";
        String insertsql = "insert into rankbyboth2_copy values (?,?,?,?,?,?)";
        //String [] params = {songName,songsterName,songsterId,regionName,singScore,smallName,score,segmentId,customerId,nickname,eventTime,winScore,songId};
        //DruidProvider dbp=new DruidProvider();
        C3poProvider dbp=new C3poProvider();
        Connection connection=dbp.getConnection();
        Statement stat=connection.createStatement();

//        ResultSet resultSet = stat.executeQuery("select * from rankbyboth2");
//        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//        for(int i=0; i<resultSetMetaData.getColumnCount(); i++) {
//            System.out.print( resultSetMetaData.getColumnLabel(i) + "\t" );
//            System.out.print( resultSetMetaData.getColumnName(i) + "\t" );
//            System.out.println( resultSetMetaData.getColumnTypeName(i) );
//        }
        ResultSet resultSet=stat.executeQuery(search);
        //PreparedStatement pstm=connection.prepareStatement(gettop);
//        while (resultSet.next()){
//            System.out.println(resultSet.getInt("rank"));
//        }

         //ResultSet resultSet1=stat.executeQuery();
        while (resultSet.next()){
            //System.out.println(resultSet.getString("count"));
            PreparedStatement pstm=connection.prepareStatement(gettop);
            pstm.setString(1,resultSet.getString("customerid"));
            ResultSet resultSet1=pstm.executeQuery();
            pstm=connection.prepareStatement(insertsql);
            while (resultSet1.next()){
                pstm.setInt(1, resultSet1.getInt("sumscore"));
                pstm.setString(2, resultSet1.getString("songsterid"));
                pstm.setString(3, resultSet1.getString("customerid"));
                pstm.setInt(4, resultSet1.getInt("count"));
                pstm.setString(5, resultSet1.getString("regionname"));
                pstm.setInt(6, resultSet1.getInt("rank"));
                pstm.executeUpdate();
            }
//        while ((line = br.readLine()) != null) {
//
//            com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
//            json = JSONObject.parseObject(line.toString());
//            String songName=json.getString("songName");
//            String songsterName=json.getString("songsterName");
//            String songsterId=json.getString("songsterId");
//            String regionName=json.getString("regionName");
//            String singScore=json.getString("singScore");
//            String smallName=json.getString("smallName");
//            String score=json.getString("score");
//            String segmentId=json.getString("segmentId");
//            String customerId=json.getString("customerId");
//            String nickname=json.getString("nickname");
//            String eventTime=json.getString("eventTime");
//            String winScore=json.getString("winScore");
//            String songId=json.getString("songId");
//
//           // System.out.println(smallName);
//
//            PreparedStatement ps=connection.prepareStatement(sql);
//            ps.setString(1,songName);
//            ps.setString(2,songsterName);
//            ps.setString(3,songsterId);
//            ps.setString(4,regionName);
//            ps.setString(5,singScore);
//            ps.setString(6,smallName);
//            ps.setString(7,score);
//            ps.setString(8,segmentId);
//            ps.setString(9,customerId);
//            ps.setString(10,nickname);
//            ps.setString(11,eventTime);
//            ps.setString(12,winScore);
//            ps.setString(13,songId);
//            ps.executeUpdate();
//            System.out.println("success");


            //QueryRunner qr = JDBCUtil.getQueryRunner();
//            qr.update(sql,params);

       // }
        //br.close();
    }



}

    public static void main(String[] args) {
        try {
            readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
