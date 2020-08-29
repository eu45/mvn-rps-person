package com.kuaizi.service;

import com.kuaizi.bean.Book;
import com.kuaizi.bean.Page;
import com.kuaizi.dao.BookDao;
import com.kuaizi.service.impl.BookImpel;

import java.util.List;

/**
 * @author kuaiziqinshi
 * @create 2020-08-23-19:09
 */
public class BookService implements BookImpel {
    private BookDao bdao=new BookDao();
    @Override
    public void insert(Book book) {
        bdao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bdao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bdao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = bdao.queryBookById(id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> books = bdao.queryBooks();
        return books;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<Book>();
        page.setPageSize(pageSize);
        Integer pageTotaleCount = bdao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotaleCount);
        int i = pageTotaleCount / pageSize;
        if (pageTotaleCount%pageSize>0){
            i+=1;
        }
        page.setPageTotal(i);
        page.setPageNO(pageNo);
        int begin=(page.getPageNO()-1)*pageSize;
        List<Book> items = bdao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        Integer pageTotaleCount = bdao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotaleCount);
        int i = pageTotaleCount / pageSize;
        if (pageTotaleCount%pageSize>0){
            i+=1;
        }
        page.setPageTotal(i);
        page.setPageNO(pageNo);
        int begin=(page.getPageNO()-1)*pageSize;
        List<Book> items = bdao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);
        return page;
    }
}
