package com.imooc.service;

import com.imooc.dto.OrderDTO;


public interface BuyerService {
    //查询单个订单
    OrderDTO findSingleOrder(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
