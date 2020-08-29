package com.kuaizi.web;

import com.kuaizi.bean.Book;
import com.kuaizi.bean.Page;
import com.kuaizi.service.BookService;
import com.kuaizi.service.impl.BookImpel;
import com.kuaizi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author kuaiziqinshi
 * @create 2020-08-24-21:27
 */

//页面书籍分页的处理
public class IndexPageServlet extends BaseServlet{
    private BookImpel service=new BookService();
    //分页
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("pageNo"));

        Object user = req.getSession().getAttribute("user");

        System.out.println("分页"+user);
        //获取页面起始页于页面容量
        int pageNo = WebUtils.parse(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parse(req.getParameter("pageSize"), Page.PAGE_SIZE);

        System.out.println(pageNo+"和"+pageSize);
        //获取页面所需展示的书籍信息
        Page<Book> page=service.page(pageNo,pageSize);
        //将url地址添加到page里
        page.setUrl("pageService?action=page");
        req.setAttribute("page",page);
        //请求转发页面
        req.getRequestDispatcher("/view/index/index.jsp").forward(req,resp);
    }

    //价格分页
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取起始页和容量
        int pageNo=WebUtils.parse(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parse(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //获取价格区间
        int min=WebUtils.parse(req.getParameter("min"),0);
        int Max=WebUtils.parse(req.getParameter("max"),Integer.MAX_VALUE);
        //通过价格区间和起始页以及容量分页
        Page<Book> page=service.pageByPrice(pageNo,pageSize,min,Max);

        StringBuilder builder = new StringBuilder("pageService?action=pageByPrice");
        //如果有最小价格的参数，则追加到分页条的地址信息
        if (req.getParameter("min")!=null){
            builder.append("&min=").append(req.getParameter("min"));
        }

        //如果有最大价格的参数，追加到分页跳的地址参数
        if (req.getParameter("max")!=null){
            builder.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(builder.toString());
        req.setAttribute("page",page);
        //转发
        req.getRequestDispatcher("/view/index/index.jsp").forward(req,resp);
    }
}
