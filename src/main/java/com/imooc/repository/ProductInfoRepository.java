package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019/10/26.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
