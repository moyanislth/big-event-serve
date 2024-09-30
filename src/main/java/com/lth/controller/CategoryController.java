package com.lth.controller;


import com.lth.pojo.Category;
import com.lth.pojo.Result;
import com.lth.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        // add category

        categoryService.add(category);
        return Result.success();
    }

}
//Authorization

//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjM1LCJ1c2VybmFtZSI6ImFkbWluIn0sImV4cCI6MTcyNzczMDIzNX0.MzgeMwnsi6anjkOpAvTHBJUi7T5nUOyYok-A8l-lGsk

