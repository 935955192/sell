package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/10/28.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService service;

    @Override
    public OrderDTO findSingleOrder(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO == null){
            log.error("【取消订单】 订单不存在，openid = {}, orderId = {}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return service.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = service.findOne(orderId);
        if(orderDTO == null){
            return null;
        }

        if(!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】 订单的openid不一致，openid = {}, orderId = {}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
