package com.kuaizi.service.impl;

import com.kuaizi.bean.Book;
import com.kuaizi.bean.Page;

import java.util.List;

/**
 * @author kuaiziqinshi
 * @create 2020-08-23-19:05
 */
public interface BookImpel {
    //添加书籍
    void insert(Book book);
    //删除书籍
    void deleteBookById(Integer id);
    //更改书籍
    void updateBook(Book book);
    //通过id查询书籍
    Book queryBookById(Integer id);
    //查询所有书籍
    List<Book> queryBooks();
    //分页
    Page<Book> page(int pageNo,int pageSize);
    //价格查询分页
    Page<Book> pageByPrice(int pageNo,int pageSize,int min,int max);

}
