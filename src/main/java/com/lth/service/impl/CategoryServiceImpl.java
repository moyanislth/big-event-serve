package com.lth.service.impl;

import com.lth.mapper.CategoryMapper;
import com.lth.pojo.Category;
import com.lth.service.CategoryService;
import com.lth.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {

        // 补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);

        log.info("add category: {}", category);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {

        // 查询当前用户所有分类

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<Category> list = categoryMapper.findCategoryList(id);
        return list;
    }

    @Override
    public Category findById(Integer id) {

        // 查询确定id的分类
        Category category = categoryMapper.findCategoryById(id);

        return category;
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleate(id);
    }
}
