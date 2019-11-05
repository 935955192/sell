package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)  也可以使用配置在applicatioin.yml中的全局配置
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
