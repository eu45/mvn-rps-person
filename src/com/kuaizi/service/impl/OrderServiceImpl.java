package com.kuaizi.service.impl;

import com.kuaizi.bean.Cart;

/**
 * @author kuaiziqinshi
 * @create 2020-08-25-19:18
 */


public interface OrderServiceImpl {
    //订单创建
    String creatOrder(Cart cart, Integer userId);
}
