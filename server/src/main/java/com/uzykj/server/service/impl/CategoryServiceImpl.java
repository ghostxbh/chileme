package com.uzykj.server.service.impl;


import com.uzykj.server.dao.CategoryMapper;
import com.uzykj.server.pojo.model.Category;
import com.uzykj.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ghostxbh
 * @Date: 11/12/2018 11:31
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByTypeIn(List<Integer> typeList) {
        return categoryMapper.findByTypeIn(typeList);
    }
}
