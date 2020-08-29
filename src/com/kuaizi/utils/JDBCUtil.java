package com.kuaizi.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @author kuaiziqinshi
 * @create 2020-08-21-21:26
 */

//事务处理工具集
public class JDBCUtil {
    private static DruidDataSource dataSource =null;
    private static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();
    //获取数据库连接池
    static {
        Properties pro=new Properties();
      //获取连接数据验证信息的一个流
        InputStream ins = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            //加载流
            pro.load(ins);
            //获取数据库连接池
            dataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取一个连接
    public static Connection getConnection(){
        Connection conn=null;
        if (conn==null) {
            try {
                //获取数据库连接池的一个连接
                conn=dataSource.getConnection();
                conns.set(conn);

                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
       return conn;
    }

    //关闭数据处理事务
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection!=null) {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    //数据事务处理的一个回滚
    public static void rollBackAndClose(){
        Connection con = conns.get();
        if (con!=null){
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }



}
