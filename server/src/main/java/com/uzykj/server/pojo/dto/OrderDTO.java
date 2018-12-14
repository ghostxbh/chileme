package com.uzykj.server.pojo.dto;


import com.uzykj.server.pojo.model.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 14:57
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> detailList;
}
