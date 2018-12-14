package com.uzykj.common;

import lombok.Data;

/**
 * 减库存入参
 * @Author: ghostxbh
 * @Date: 14/12/2018 14:46
 */
@Data
public class DecreaseStockInput
{

    private String productId;

    private Integer productQuantity;//数量

    public DecreaseStockInput(String productId, Integer productQuantity)
    {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public DecreaseStockInput()
    {
    }
}
