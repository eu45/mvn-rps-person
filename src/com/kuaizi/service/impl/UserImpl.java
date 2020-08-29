package com.kuaizi.service.impl;

import com.kuaizi.bean.User;

/**
 * @author kuaiziqinshi
 * @create 2020-08-22-19:29
 */
public interface UserImpl {
    //注册
    void registerUser(User user);
    //登录
    User loginUser(User user);
    //检查是否能注册该账号
    boolean existsUserName(String name);
}
