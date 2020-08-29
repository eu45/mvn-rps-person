package com.kuaizi.web;

import com.google.gson.Gson;
import com.kuaizi.bean.Book;
import com.kuaizi.bean.Cart;
import com.kuaizi.bean.CartItem;
import com.kuaizi.service.BookService;
import com.kuaizi.service.impl.BookImpel;
import com.kuaizi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author kuaiziqinshi
 * @create 2020-08-24-21:13
 */
public class CartServlet extends BaseServlet {
    private BookImpel service=new BookService();
    //修改商品
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parse(req.getParameter("id"), 0);
        int count = WebUtils.parse(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
    //删除商品
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parse(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    //添加书籍到购物车
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parse(req.getParameter("id"), 0);
        Object user = req.getSession().getAttribute("user");
        Book book = service.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());

        System.out.println("这个"+cart);
        System.out.println(req.getHeader("Referer")+"ha?");
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //通过ajax购物车添加
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parse(req.getParameter("id"),0);
        //获取页面的需要购买的书籍信息
        Book book = service.queryBookById(id);
        //将书籍添加到购物车项当中
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
       //从会话当中获取购物车判断是否为空，为空在创建，不为空则添加到购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
       if (cart==null){
           cart= new Cart();
           req.getSession().setAttribute("cart",cart);
       }
       cart.addItem(cartItem);
        System.out.println(cart);
        req.getSession().setAttribute("lastName",cartItem.getName());
        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> map=new HashMap<>();

        map.put("totalCount",cart.getTotalCount());
        map.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String mapGson = gson.toJson(map);

        resp.getWriter().write(mapGson);
    }









//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
////        resp.setContentType("text/html;charset=UTF-8");
//        System.out.println("进入了cartServlet");
//        String action=req.getParameter("action");
//        System.out.println(action+"?");
//        try {
//            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            declaredMethod.invoke(this,req,resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
