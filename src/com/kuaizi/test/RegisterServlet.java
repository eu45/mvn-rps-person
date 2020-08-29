//package com.kuaizi.test;
//
//import com.kuaizi.bean.User;
//import com.kuaizi.service.UserService;
//import com.kuaizi.service.impl.UserImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
//
///**
// * @author kuaiziqinshi
// * @create 2020-08-21-21:14
// */
//public class RegisterServlet extends HttpServlet {
//    private UserImpl userIpel=new UserService();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.registerFirst(req,resp);
//    }
//
//    protected void registerFirst(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
////        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
////        String code=req.getParameter("code");
////        if (token!=null&&token.equalsIgnoreCase(code)){
//            if (!userIpel.existsUserName(username)){
//                req.setAttribute("username",username);
//                req.setAttribute("email",email);
//                req.getRequestDispatcher("/view/User/regist.jsp").forward(req,resp);
//            }else {
//                userIpel.registerUser(new User(username,password,email));
//                req.getRequestDispatcher("/view/User/login.jsp").forward(req,resp);
//            }
////        }else{
////            req.setAttribute("msg","验证码出错！");
////            req.setAttribute("username",username);
////            req.setAttribute("email",email);
////            System.out.println("验证码["+code+"]错误");
////            req.getRequestDispatcher("view/User/register.jsp").forward(req,resp);
////        }
//
//
//    }
//}
