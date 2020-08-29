package com.kuaizi.web;

import com.kuaizi.bean.User;
import com.kuaizi.dao.UserDao;
import com.kuaizi.service.UserService;
import com.kuaizi.service.impl.UserImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author kuaiziqinshi
 * @create 2020-08-23-19:13
 */
public class UserServlet extends HttpServlet{
    private UserImpl userfir=new UserService();
    //登录验证
    protected void first(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user1=new User(username,password);
        System.out.println(username+"   "+password);
       //如果登陆成功，转入到购物页面，否则转入注册页面
        if (userfir.loginUser(user1)!=null){
            //创建一个session，将登陆成功的用户信息放入session。
            req.getSession().setAttribute("user",user1);

            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }else {
            //登入不成功也将用户的信息转入到注册页面
            req.getSession().setAttribute("user",user1);
            resp.sendRedirect("/BookStore/view/User/regist.jsp");
//            req.getRequestDispatcher("/view/User/regist.jsp").forward(req, resp);
        }
    }

    //注销用户信息，销毁会话即可注销
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    //通过dopost处理，转入到本类的相应方法进行处理相应业务
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("registerFirst".equals(req.getParameter("action"))){
        this.registerFirst(req,resp);}
        if ("logout".equals(req.getParameter("action"))){
            doGet(req,resp);
        }if ("first".equals(req.getParameter("action"))){
            this.first(req,resp);
        }
    }
//登出使用get方法传递
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logout(req,resp);
    }
//注册用户信息
    protected void registerFirst(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //验证码的获取
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code=req.getParameter("code");
        //判断验证码是否正确
        if (token!=null&&token.equalsIgnoreCase(code)){
            //检查用户名是否可用
            if (!userfir.existsUserName(username)){
            //如果存在则报错重新注册
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/view/User/regist.jsp").forward(req,resp);
        }else {
            //用户注册
            userfir.registerUser(new User(username,password,email));
            //注册成功后，转入到登录页面进行操作
            req.getRequestDispatcher("/view/User/login.jsp").forward(req,resp);
        }
        }else{
            //验证码出错的反馈，重新注册
            req.setAttribute("msg","验证码出错！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("view/User/register.jsp").forward(req,resp);
        }


    }
}
