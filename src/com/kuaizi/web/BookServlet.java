package com.kuaizi.web;

import com.kuaizi.bean.Book;
import com.kuaizi.bean.Page;
import com.kuaizi.service.BookService;
import com.kuaizi.service.impl.BookImpel;
import com.kuaizi.utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kuaiziqinshi
 * @create 2020-08-23-19:12
 */
//处理书籍相关操作
public class BookServlet extends BaseServlet {
   private BookImpel service=new BookService();
   //处理分页
   protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println(req.getParameter("pageNo"));
       //获取起始页的整型数据
       int pageNo = WebUtils.parse(req.getParameter("pageNo"), 1);
       //获取页面容量的整型数据
       int pageSize = WebUtils.parse(req.getParameter("pageSize"), Page.PAGE_SIZE);

       System.out.println(pageNo+"和"+pageSize);
       //处理分页
       Page<Book> page=service.page(pageNo,pageSize);
       //页面转载的设置
       page.setUrl("bookService?action=page");
       //将获取的页面信息传递到jsp
       req.setAttribute("page",page);
       req.getRequestDispatcher("/view/manager/book_manager.jsp").forward(req,resp);
   }
   //查询书本信息
   protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int id = WebUtils.parse(req.getParameter("id"), 0);
       Book book = service.queryBookById(id);
       req.setAttribute("book",book);
       req.getRequestDispatcher("/view/manager/book_edit.jsp").forward(req,resp);
   }
   //获取所有图书信息
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books=service.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/view/manager/book_manager.jsp").forward(req,resp);
    }
    //添加图书
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int pageNo=WebUtils.parse(req.getParameter("pageNo"),0);
       pageNo+=1;
        Book book= WebUtils.insertBean(req.getParameterMap(),new Book());
        service.insert(book);
        System.out.println(book);
       resp.sendRedirect(req.getContextPath()+"/bookService?action=page&pageNo="+pageNo);
    }
    //删除图书
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int ds = WebUtils.parse(id, 0);
        service.deleteBookById(ds);
        resp.sendRedirect(req.getContextPath()+"/bookService?action=page&pageNo="+req.getParameter("pageNo"));
    }
    //修改图书信息
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.insertBean(req.getParameterMap(), new Book());
        service.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/bookService?action=page&pageNo="+req.getParameter("pageNo"));
    }
}
