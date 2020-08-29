package com.kuaizi.web;

import com.kuaizi.bean.Cart;
import com.kuaizi.bean.User;
import com.kuaizi.service.OrderService;
import com.kuaizi.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kuaiziqinshi
 * @create 2020-08-24-22:39
 */

//订单的处理
public class OrderServlet extends BaseServlet{
    private OrderServiceImpl service=new OrderService();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取用户信息
        User user = (User) req.getSession().getAttribute("user");
        //如果没登入去登入
        if (user==null){
            req.getRequestDispatcher("/view/User/login.jsp").forward(req,resp);
            return;
        }
        Integer id = user.getId();
        //创建订单并将订单信息保存到session当中
        String s = service.creatOrder(cart, id);
        req.getSession().setAttribute("orderId",s);
        //转发
        resp.sendRedirect(req.getContextPath()+"/view/cart/checkout.jsp");
    }
}
