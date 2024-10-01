package com.lth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lth.mapper.ArticleMapper;
import com.lth.pojo.Article;
import com.lth.pojo.PageBean;
import com.lth.service.ArticleService;
import com.lth.utils.ThreadLocalUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.addArticle(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSiz , Integer categoryId, String state) {

        // 创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();

        // 开启分页查询,使用mybatis的分页插件
        PageHelper.startPage(pageNum, pageSiz);

        // 调用Mapper查询
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        List<Article> list = articleMapper.list(id, categoryId, state);

        // Page中提供了方法可以获取Helper分页查询后的总记录条数和当前页数据
        Page<Article> page = (Page<Article>) list;

        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return pageBean;
    }
}
