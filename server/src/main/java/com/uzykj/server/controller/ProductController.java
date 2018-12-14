package com.uzykj.server.controller;


import com.uzykj.server.pojo.dto.CartDTO;
import com.uzykj.server.pojo.model.Category;
import com.uzykj.server.pojo.model.Product;
import com.uzykj.server.pojo.vo.ProductInfoVO;
import com.uzykj.server.pojo.vo.ProductVO;
import com.uzykj.server.pojo.vo.ResultVO;
import com.uzykj.server.service.CategoryService;
import com.uzykj.server.service.ProductService;
import com.uzykj.server.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xu
 * @DateTime 2018-12-11
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 1、查询所有在架的商品
     * 2、获取类目category列表
     * 3、查询类目
     * 4、构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
        //1、查询所有在架的商品
        List<Product> productList = productService.findUpAll();
        //2、获取类目category列表
        List<Integer> categoryTypeList = productList.stream()
                .map(Product::getCategory)
                .collect(Collectors.toList());
        //3、查询类目
        List<Category> categoryList = categoryService.findByTypeIn(categoryTypeList);
        //4、构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (Category category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getName());
            productVO.setCategoryType(category.getType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (Product product : productList) {
                if (product.getCategory().equals(category.getType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(product, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductList(productInfoVOList);
            productVOList.add(productVO);
        }
        ResultVO resultVo = null;
        if (productVOList.size() > 0) {
            resultVo = ResultVOUtil.success(productVOList);
        } else {
            resultVo = ResultVOUtil.fail();
        }
        return resultVo;
    }

    /**
     * 获取商品列表（给订单服务调用的）
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<Product> listForOrder(@RequestBody List<Integer> productIdList) {
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }
}
