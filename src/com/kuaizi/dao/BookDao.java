package com.kuaizi.dao;

import com.kuaizi.bean.Book;
import com.kuaizi.bean.Page;
import com.kuaizi.dao.impl.BaseDao;
import com.kuaizi.dao.impl.BookDaoImpl;

import javax.print.attribute.standard.NumberUp;
import java.util.List;

/**
 * @author kuaiziqinshi
 * @create 2020-08-22-22:47
 */
public class BookDao extends BaseDao implements BookDaoImpl {
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
        Integer integer = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
        return integer;
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        Integer i = update(sql, id);
        return i;
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id= ?";
        System.out.println(book.toString());
        Integer in = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path(),book.getId());
        return in;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select *from t_book where id=?";
        Book book = queryForOne(Book.class, sql, id);
        return book;
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";

        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select *from t_book";
        List<Book> books = queryForList(Book.class, sql);
        return books;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id , name , author , price , sales , stock , img_path imgPath from t_book limit ?,?";

        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number total = (Number) queryForSingleValue(sql, min, max);
        return total.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";

        return queryForList(Book.class,sql,min,max,begin);
    }


}
