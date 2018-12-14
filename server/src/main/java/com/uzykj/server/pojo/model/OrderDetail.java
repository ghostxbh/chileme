package com.uzykj.server.pojo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 14:20
 */
@Data
@Entity
public class OrderDetail {

    @Id
    private String id;

    private Integer orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;
}
