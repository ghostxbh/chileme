package com.uzykj.server.pojo.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.uzykj.server.pojo.model.Product;
import lombok.Data;

import java.util.List;


/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 11:37
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<Product> productList;
}
