package com.imooc.service;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2019/10/26.
 */
public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO findOne(String orderId);
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    OrderDTO cancel(OrderDTO orderDTO);
    OrderDTO finish(OrderDTO orderDTO);
    OrderDTO pay(OrderDTO orderDTO);

}
