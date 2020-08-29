package com.kuaizi.dao.impl;

import com.kuaizi.bean.User;
import com.kuaizi.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.jws.soap.SOAPBinding;
import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author kuaiziqinshi
 * @create 2020-08-21-21:41
 */
public class BaseDao {
    private QueryRunner queryRunner=new QueryRunner();
    //保存账号和密码
    public Integer update(String sql,Object ...args){
        Connection conn= JDBCUtil.getConnection();

        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
           throw new RuntimeException(throwables);
        }finally {
            JDBCUtil.commitAndClose();
        }
    }
    //通过名称，查询账号
    public Object queryForSingleValue(String sql,Object ...args) {
        Connection connection = JDBCUtil.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }finally {
            JDBCUtil.commitAndClose();
        }
    }
    //通过密码和用户名查询账号
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection connection = JDBCUtil.getConnection();
        try {
            //beanHandler处理bean和来自数据库的对象进行映射
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.commitAndClose();
        }

    }
    //获取所有用户信息
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn=JDBCUtil.getConnection();
        try {
           return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.commitAndClose();
        }
    }
}
