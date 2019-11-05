package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http:xxxx.jpg");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductId("123");
        orderDetail.setProductPrice(new BigDecimal(2.2));;
        orderDetail.setDetailId("234255");
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetails = repository.findByOrderId("123456");
        Assert.assertNotEquals(0, orderDetails.size());
    }

}