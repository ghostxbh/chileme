package com.uzykj.server.dao;


import com.uzykj.server.pojo.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 14:26
 */
public interface OrderDetailMapper extends JpaRepository<OrderDetail,Integer> {

}
