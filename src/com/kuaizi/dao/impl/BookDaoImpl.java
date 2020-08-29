package com.kuaizi.dao.impl;

import com.kuaizi.bean.Book;
import com.kuaizi.bean.Page;

import java.util.List;

/**
 * @author kuaiziqinshi
 * @create 2020-08-22-22:47
 */
public interface BookDaoImpl {
    //添加书籍
    int addBook(Book book);
    //通过id删除书籍
    int deleteBookById(Integer id);
    //更新书籍信息
    int updateBook(Book book);
    //通过id查询书籍信息
    Book queryBookById(Integer id);
    //获取页面计数
    Integer queryForPageTotalCount();
    //获取所有的书本信息
    List<Book> queryBooks();
    //获取当前页面的书籍展示数据
    List<Book> queryForPageItems(int begin,int pageSize);
    //通过价格区间获取符合该条件的所有书籍总数
    Integer queryForPageTotalCountByPrice(int min,int max);
    //获取当前页面的所需要展示的书籍信息
    List<Book> queryForPageItemsByPrice(int begin,int pageSize,int min,int max);
}
