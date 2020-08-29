package com.kuaizi.web;

import com.kuaizi.bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author kuaiziqinshi
 * @create 2020-08-21-19:49
 */

//一个基础servlet类处理对jsp传来的数据信息的一个简单处理
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将req请求的字符集更改为utf8
        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
//        System.out.println("进入了BaseServlet");
        String action=req.getParameter("action");
        //将用户的信息加载到会话当中
        User user= (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("user",user);
//        System.out.println(action);
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
