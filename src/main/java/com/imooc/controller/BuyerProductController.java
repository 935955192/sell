package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductInfoService;
import com.imooc.util.ResultVOUtil;
import com.imooc.vo.ProductInfoVO;
import com.imooc.vo.ProductVO;
import com.imooc.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/10/26.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResultVO list(){
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
//        List<Integer> categoryTypeList = new ArrayList<Integer>();
//
//        for (ProductInfo info : productInfoList) {
//            categoryTypeList.add(info.getCategoryType());
//        }
        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo info : productInfoList) {
                if(info.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //拷贝属性
                    BeanUtils.copyProperties(info,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
