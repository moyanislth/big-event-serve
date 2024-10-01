package com.lth.service;


import com.lth.pojo.Category;

import java.util.List;

public interface CategoryService {

    // 添加分类
    void add(Category category);

    // 查询分类列表
    List<Category> list();

    // 查询具体id分类
    Category findById(Integer id);

    // 更新分类
    void update(Category category);

    void delete(Integer id);
}
