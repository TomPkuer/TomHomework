package util;

import com.alibaba.druid.pool.DruidDataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.FileInputStream;

import java.sql.SQLException;

import java.util.Properties;


public class DBUtil {

    private static DruidDataSource druidDataSource = null;

    static {

        try {

            Properties props = new Properties();

            props.load(new FileInputStream("src/db.properties"));

            druidDataSource = (DruidDataSource) DruidDataSourceFactory

                    .createDataSource(props);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static DruidPooledConnection getConnection() {

        DruidPooledConnection connection = null;

        try {

            connection = druidDataSource.getConnection();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        if (connection == null) {

            System.exit(0);

        }

        return connection;

    }



}

