package com.kuaizi.filter;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;

/**
 * @author kuaiziqinshi
 * @create 2020-08-25-21:57
 */
public class ManagerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //过滤器拦截验证是否登录
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServlet=(HttpServletRequest) servletRequest;
        //获取session会话当中的用户信息
        Object user = httpServlet.getSession().getAttribute("user");
        //如果为空，转到登陆页面
        if (user==null){

            httpServlet.getRequestDispatcher("/view/User/login.jsp").forward(servletRequest,servletResponse);
        }else{

            filterChain.doFilter(servletRequest,servletResponse);

        }
    }

    @Override
    public void destroy() {

    }
}
