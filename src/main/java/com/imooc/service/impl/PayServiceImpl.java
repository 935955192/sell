package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.PayService;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/11/3.
 */
@Service
public class PayServiceImpl implements PayService {
    @Override
    public void create(OrderDTO orderDTO) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        WxPayH5Config wxPayH5Config;
        //bestPayService.setWxPayH5Config();
    }
}
