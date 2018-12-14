package com.uzykj.server.service.impl;

import com.uzykj.client.ProductClient;
import com.uzykj.common.ProductInfoOutput;
import com.uzykj.server.dao.OrderDetailMapper;
import com.uzykj.server.dao.OrderMasterMapper;
import com.uzykj.server.enums.OrderStatusEnum;
import com.uzykj.server.enums.PayStatusEnum;
import com.uzykj.server.pojo.dto.CartDTO;
import com.uzykj.server.pojo.dto.OrderDTO;
import com.uzykj.server.pojo.model.OrderDetail;
import com.uzykj.server.pojo.model.OrderMaster;
import com.uzykj.server.service.OrderService;
import com.uzykj.server.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 15:00
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String code = KeyUtil.getUniqueKey();
        //查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputs = productClient.listForOrder(productIdList);
        //计算总价
        BigDecimal amount = null;
        for (OrderDetail orderDetail : orderDTO.getDetailList()) {
            for (ProductInfoOutput productInfoOutput : productInfoOutputs) {
                if (orderDetail.getProductId().equals(productInfoOutput.getProductId())) {
                    //单价*数量
                    amount = productInfoOutput.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(amount);
                    BeanUtils.copyProperties(productInfoOutput, orderDetail);
                    //订单详情入库
                    orderDetailMapper.save(orderDetail);
                }
        }
    }
        //扣库存（调用商品服务）
        List<CartDTO> cartDTOList = orderDTO.getDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setCode(KeyUtil.getUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setAmount(Double.valueOf(amount));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.save(orderMaster);
        return orderDTO;
    }
}
