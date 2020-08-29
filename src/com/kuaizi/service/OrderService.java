package com.kuaizi.service;

import com.kuaizi.bean.*;
import com.kuaizi.dao.BookDao;
import com.kuaizi.dao.OrderDao;
import com.kuaizi.dao.OrderItemDao;
import com.kuaizi.dao.impl.BookDaoImpl;
import com.kuaizi.dao.impl.OrderDaoImpl;
import com.kuaizi.dao.impl.OrderItemImpl;
import com.kuaizi.service.impl.OrderServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;
import java.util.Map;

/**
 * @author kuaiziqinshi
 * @create 2020-08-25-19:18
 */
public class OrderService implements OrderServiceImpl {
    private OrderDaoImpl orderDao=new OrderDao();
    private OrderItemImpl orderItem=new OrderItemDao();
    private BookDaoImpl bookDao=new BookDao();
    @Override
    public String creatOrder(Cart cart, Integer userId) {
        String orderId=System.currentTimeMillis()+" "+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem1 = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                                                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItem.saveOrderItem(orderItem1);
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
