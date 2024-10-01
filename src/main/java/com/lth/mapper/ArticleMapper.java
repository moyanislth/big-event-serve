package com.lth.mapper;

import com.lth.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    // Add article
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void addArticle(Article article);


    // 分页查询文章,在同级目录下创建ArticleMapper.xml文件配置
    List<Article> list(Integer id, Integer categoryId, String state);




}
