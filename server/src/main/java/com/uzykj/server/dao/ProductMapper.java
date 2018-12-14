package com.uzykj.server.dao;




import com.uzykj.server.pojo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMapper extends JpaRepository<Product,Integer> {


    List<Product> findByStatus(Integer status);

    List<Product> findByIdIn(List<Integer> idList);
}
