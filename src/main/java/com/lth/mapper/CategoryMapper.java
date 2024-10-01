package com.lth.mapper;

import com.lth.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // add category
    @Insert("INSERT INTO category (category_name, category_alias, create_user, create_time, update_time) VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    // query category list
    @Select("SELECT * FROM category WHERE create_user = #{createUser}")
    List<Category> findCategoryList(Integer createUser);

    // query category by id
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findCategoryById(Integer id);

    // update category
    @Update("UPDATE category SET category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = NOW() WHERE id = #{id}")
    void update(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleate(Integer id);
}
