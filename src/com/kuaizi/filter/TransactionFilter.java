package com.kuaizi.filter;

import com.kuaizi.utils.JDBCUtil;

import javax.servlet.*;
import javax.sql.rowset.JdbcRowSet;
import java.io.IOException;

/**
 * @author kuaiziqinshi
 * @create 2020-08-25-21:57
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    //对数据的关闭
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
      //执行过滤器链中当前的过滤器
            filterChain.doFilter(servletRequest,servletResponse);

            JDBCUtil.commitAndClose();
        } catch (Exception e) {
           //事物的回滚
            JDBCUtil.rollBackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
