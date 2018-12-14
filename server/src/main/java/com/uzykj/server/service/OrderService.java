package com.uzykj.server.service;


import com.uzykj.server.pojo.dto.OrderDTO;

/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 14:56
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
