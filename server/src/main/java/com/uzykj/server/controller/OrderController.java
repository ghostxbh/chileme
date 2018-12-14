package com.uzykj.server.controller;


import com.uzykj.server.pojo.dto.OrderDTO;
import com.uzykj.server.pojo.vo.ResultVO;
import com.uzykj.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 14:53
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
//    @Autowired
//    private ProductClient productClient;
    /**
     * 1、参数校验
     * 2、查询商品信息（调用商品服务）
     * 3、计算总价
     * 4、扣库存（调用商品服务）
     * 5、订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@RequestBody OrderDTO orderDTO){
        Map<String,String> map = new HashMap<>();
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        map.put("orderDTO",orderDTO1.toString());
        return new ResultVO(1,"OK",map);
    }
}
