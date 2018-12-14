package com.uzykj.server.dao;



import com.uzykj.server.pojo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMapper extends JpaRepository<Category,Integer> {

    List<Category> findByTypeIn(List<Integer> typeList);
}
