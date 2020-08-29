package com.kuaizi.dao;

import com.kuaizi.bean.User;
import com.kuaizi.dao.impl.BaseDao;
import com.kuaizi.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @author kuaiziqinshi
 * @create 2020-08-21-21:01
 */
public class UserDao extends BaseDao implements UserDaoImpl {

    @Override
    public  void save(User user){
     String sql="insert into t_user(username,password,email)values(?,?,?)";
     update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public  User queryUserByName(String name) {
        String sql="select *from t_user where username=?";
        User user = queryForOne(User.class, sql, name);
        return user;
    }

    @Override
    public User queryUserByAll(String name, String password) {
        String sql="select *from t_user where username=? and password=?";
        User user = (User) queryForOne(User.class,sql,name,password);
        return user;
    }
}
