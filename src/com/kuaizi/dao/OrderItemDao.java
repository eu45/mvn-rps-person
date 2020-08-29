package com.kuaizi.dao;

import com.kuaizi.bean.OrderItem;
import com.kuaizi.dao.impl.BaseDao;
import com.kuaizi.dao.impl.OrderItemImpl;

/**
 * @author kuaiziqinshi
 * @create 2020-08-24-22:59
 */
public class OrderItemDao extends BaseDao implements OrderItemImpl {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";


        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
