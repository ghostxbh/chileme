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
 * @Date: 11/12/2018 14:00
 */
@Data
@Entity
public class OrderMaster {

    @Id
    private String id;

    private String code;

    private String name;

    private String phone;

    private String address;

    private String openid;

    private BigDecimal amount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;
}
