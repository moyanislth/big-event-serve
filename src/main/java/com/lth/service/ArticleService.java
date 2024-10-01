package com.lth.service;

import com.lth.pojo.Article;
import com.lth.pojo.PageBean;

public interface ArticleService {

    // Add article
    void addArticle(Article article);

    // 分页查询文章
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
