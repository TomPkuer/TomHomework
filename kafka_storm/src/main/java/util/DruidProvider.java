package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;


public class DruidProvider {
    private static DruidProvider dbPoolConnection = null;
    private static DruidDataSource druidDataSource = null;

    static {
        Properties properties = loadPropertiesFile("db.properties");
        try {
            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);    //DruidDataSrouce工厂模式
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库连接池单例
     * @return
     */
    public static synchronized DruidProvider getInstance(){
        if (null == dbPoolConnection){
            dbPoolConnection = new DruidProvider();
        }
        return dbPoolConnection;
    }

    /**
     * 返回druid数据库连接
     * @return
     * @throws SQLException
     */
    public DruidPooledConnection getConnection() throws SQLException{
        return druidDataSource.getConnection();
    }

    private static Properties loadPropertiesFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals("")){
            throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
        }
        webRootPath = DruidProvider.class.getClassLoader().getResource("").getPath();
        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p =null;
        try {
            inputStream = new FileInputStream("src/db.properties");
            p = new Properties();
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return p;
    }

}