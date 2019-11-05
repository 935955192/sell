package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> categories = categoryService.findAll();
        Assert.assertEquals(3, categories.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(Arrays.asList(2, 3, 4));
        Assert.assertEquals(3, categories.size());
    }

//    @Test
//    public void save() throws Exception {
//        ProductCategory category = new ProductCategory("热销榜", 2);
//        Assert.assertEquals(new Integer(3),categoryService.save(category).getCategoryId());
//    }

}