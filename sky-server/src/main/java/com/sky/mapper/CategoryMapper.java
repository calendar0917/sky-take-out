package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

    // 新增菜品
    @AutoFill
    @Insert("insert into category values (default, #{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void addCategory(Category category);

    // 分页查询
    Page<Category> pageQueryCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    // 删除分类
    @Delete("delete from category where id = #{id}")
    void DeleteById(Long id);

    // 修改菜品
    @AutoFill(OperationType.UPDATE)
    void UpdateById(Category category);


}
