package com.uzykj.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: ghostxbh
 * @Date: 14/12/2018 14:42
 */
@Data
public class ProductInfoOutput {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;//库存

    private String productDescription;//描述

    private String productIcon;//小图

    private Integer productStatus;//状态：0、正常；1、下架

    private Integer category;
}
