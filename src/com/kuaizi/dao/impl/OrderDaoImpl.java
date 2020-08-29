package com.kuaizi.dao.impl;

import com.kuaizi.bean.Order;

/**
 * @author kuaiziqinshi
 * @create 2020-08-24-22:51
 */
public interface OrderDaoImpl {
    //创建一个订单
    int saveOrder(Order order);
}
