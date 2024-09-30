package com.lth.mapper;

import com.lth.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    // add category
    @Insert("INSERT INTO category (category_name, category_alias, create_user, create_time, update_time) VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

}
