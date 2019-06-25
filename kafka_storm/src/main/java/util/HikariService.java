package util;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public  class HikariService {
    private static Logger logger = LoggerFactory.getLogger(HikariService.class);

    private static final String DB_CONFIG_FILE = "/db.properties";

    public static HikariDataSource dataSource;

    // 数据库连接数
    private short db_max_conn = 0;

    // 数据库服务器addr
    private String db_url = null;

    // 数据库连接端口
    private short db_port = 0;

    // 数据库名称
    private String db_name = null;

    // 数据库登录用户名
    private String db_username = null;

    // 数据库登录密码
    private String db_password = null;

    // 数据库连接
    private Connection connection;

    private static HikariService dBService;
    public static HikariService getInstance(){
        if (dBService == null) {
            dBService = new HikariService();
        }
        return dBService;
    }

    public void start() throws IOException, SQLException {
        Properties properties = new Properties();
        //InputStream in = HikariService.class.getClass().getResourceAsStream(DB_CONFIG_FILE);
        properties.load(new FileInputStream("src/db.properties"));

        db_max_conn = Short.valueOf(properties.getProperty("db_max_conn"));
        db_url = String.valueOf(properties.getProperty("db_url"));
        db_port = Short.valueOf(properties.getProperty("db_port"));
        db_name = String.valueOf(properties.getProperty("db_name"));
        db_username = String.valueOf(properties.getProperty("db_username"));
        db_password = String.valueOf(properties.getProperty("db_password"));

        if (db_url == null || db_url.length() == 0) {
            logger.error("配置的数据库ip地址错误!");
            System.exit(0);
        }

        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(db_max_conn);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", db_url);
        config.addDataSourceProperty("port", db_port);
        config.addDataSourceProperty("databaseName", db_name);
        config.addDataSourceProperty("user", db_username);
        config.addDataSourceProperty("password", db_password);
        dataSource = new HikariDataSource(config);

    }


    public  Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.resumePool();
            return null;
        }    }

    public boolean stop() throws SQLException {
        dataSource.close();
        return true;
    }
}
