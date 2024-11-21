package com.lth.controller;


import com.lth.pojo.Category;
import com.lth.pojo.PageBean;
import com.lth.pojo.Result;
import com.lth.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        // add category
        log.info("category: {}", category);
        categoryService.add(category);
        return Result.success();
    }


    @GetMapping
    public Result<List<Category>> list() {

        // 查询当前用户所有分类
        List<Category> list = categoryService.list();

        return Result.success(list);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id) {

        // 查询确定id的分类
        Category category = categoryService.findById(id);

        if (category == null) {
            return Result.error("Category does not exist");
        }

        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {

        Category ct = categoryService.findById(category.getId());
        if (ct == null) {
            return Result.error("Category does not exist");
        }

        // update category
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {

        Category category = categoryService.findById(id);
        if (category == null) {
            return Result.error("Category does not exist");
        }

        // delete category
        categoryService.delete(id);
        return Result.success();
    }

}
//Authorization

//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjM1LCJ1c2VybmFtZSI6ImFkbWluIn0sImV4cCI6MTcyNzczMDIzNX0.MzgeMwnsi6anjkOpAvTHBJUi7T5nUOyYok-A8l-lGsk

