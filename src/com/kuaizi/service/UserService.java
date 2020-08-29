package com.kuaizi.service;

import com.kuaizi.bean.User;
import com.kuaizi.dao.UserDao;
import com.kuaizi.dao.impl.UserDaoImpl;
import com.kuaizi.service.impl.UserImpl;
import org.junit.Test;

/**
 * @author kuaiziqinshi
 * @create 2020-08-22-19:28
 */
public class UserService implements UserImpl {
    private UserDaoImpl userDao=new UserDao();

    @Override
    public void registerUser(User user) {
        userDao.save(user);
    }

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByAll(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUserName(String name) {
         if (userDao.queryUserByName(name)==null){
             return true;
         }
        return false;
    }
}
