package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2019/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = service.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> infos = service.findUpAll();
        Assert.assertNotEquals(0, infos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> infos = service.findAll(pageRequest);
        System.out.println(infos.getTotalElements());
        Assert.assertNotEquals(0, infos.getSize());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DONW.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = service.save(productInfo);
        Assert.assertNotNull(result);
    }

}