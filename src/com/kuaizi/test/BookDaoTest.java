//package com.kuaizi.test;
//
//import com.kuaizi.bean.Book;
//import com.kuaizi.dao.impl.BaseDao;
//import com.kuaizi.dao.impl.BookDaoImpl;
//
//import java.util.List;
//
///**
// * @author kuaiziqinshi
// * @create 2020-08-22-22:47
// */
//public class BookDaoTest extends BaseDao implements BookDaoImpl {
//    @Override
//    public int addBook(Book book) {
//        String sql="insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
//        Integer integer = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
//        return integer;
//    }
//
//    @Override
//    public int deleteBookById(Integer id) {
//        String sql="delete from t_table where id=?";
//        Integer i = update(sql, id);
//        return i;
//    }
//
//    @Override
//    public int updateBook(Book book) {
//        String sql="update from t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
//        Integer in = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_path());
//        return in;
//    }
//
//    @Override
//    public Book queryBookById(Integer id) {
//        return null;
//    }
//
//    @Override
//    public List<Book> queryBooks() {
//        return null;
//    }
//}
