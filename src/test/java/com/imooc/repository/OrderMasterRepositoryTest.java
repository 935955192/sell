package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setBuyerOpenid("110110");

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid(OPENID, pageRequest);
//        System.out.println(orderMasterPage.getTotalElements());
        Assert.assertNotEquals(0, orderMasterPage.getTotalElements());
    }
}