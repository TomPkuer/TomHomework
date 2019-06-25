package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConn {
    public static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", "192.168.97.147");
        config.addDataSourceProperty("port", 3306);
        config.addDataSourceProperty("databaseName", "songOrder");
        config.addDataSourceProperty("user","root");
        config.addDataSourceProperty("password", "666666");
        dataSource = new HikariDataSource(config);
    }
    public Connection getConnection() throws SQLException {
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
