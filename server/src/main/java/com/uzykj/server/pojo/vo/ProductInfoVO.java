package com.uzykj.server.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * @Author: ghostxbh
 * @Date: 14/12/2018 19:25
 */
@Data
public class ProductInfoVO {

    private String id;

    private String name;

    private BigDecimal price;

    private String description;//描述

    private String icon;//小图
}
