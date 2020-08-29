package com.kuaizi.dao;

import com.kuaizi.bean.Order;
import com.kuaizi.dao.impl.BaseDao;
import com.kuaizi.dao.impl.OrderDaoImpl;
import org.omg.CORBA.Object;

/**
 * @author kuaiziqinshi
 * @create 2020-08-24-22:52
 */
public class OrderDao extends BaseDao implements OrderDaoImpl {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

}
