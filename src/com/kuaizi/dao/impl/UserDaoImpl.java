package com.kuaizi.dao.impl;

import com.kuaizi.bean.User;

/**
 * @author kuaiziqinshi
 * @create 2020-08-21-21:03
 */
public interface UserDaoImpl {
    //保存用户信息
    void save(User user);
    //通过名字获取用户信息
    User queryUserByName(String name);
    //密码和用户名获取信息
    User queryUserByAll(String name,String password);

}
