package com.kuaizi.test;

import com.kuaizi.bean.User;
import com.kuaizi.dao.UserDao;
import org.junit.Test;

/**
 * @author kuaiziqinshi
 * @create 2020-08-21-21:53
 */
public class TestDemo {
    @Test
    public void test(){
        User user=new User("老王","sss","sq@qq.com");
        UserDao dao=new UserDao();
        try {
            dao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
