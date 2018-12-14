package com.uzykj.server.service.impl;


import com.uzykj.server.dao.ProductMapper;
import com.uzykj.server.exception.ProductException;
import com.uzykj.server.pojo.dto.CartDTO;
import com.uzykj.server.enums.ProductEnums;
import com.uzykj.server.enums.ResultEnum;
import com.uzykj.server.pojo.model.Product;
import com.uzykj.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findUpAll() {
        return productMapper.findByStatus(ProductEnums.UP.getCode());
    }

    @Override
    public List<Product> findList(List<Integer> productIdList) {
        return productMapper.findByIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cart : cartDTOList) {
            Optional<Product> byId = productMapper.findById(cart.getProductId());
            //判断库存是否存在
            if (!byId.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Product product = byId.get();
            Integer result = product.getStock() - cart.getProductQuantity();
            //判断库存是否足够
            if (result <= 0){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            product.setStock(result);
            productMapper.save(product);
        }
    }
}
