package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult pageQueryCategory(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQueryCategory(categoryPageQueryDTO);

        List<Category> categoryList = page.getResult();
        Long count = page.getTotal();
        PageResult pageResult = new PageResult();
        pageResult.setTotal(count);
        pageResult.setRecords(categoryList);
        return pageResult;
    }

    // 新增菜品
    @Override
    public void addCategory(CategoryDTO categotyDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categotyDTO, category);
        category.setStatus(StatusConstant.ENABLE);
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());

        categoryMapper.addCategory(category);
    }

    @Override
    public void DeleteById(Integer id) {
        categoryMapper.DeleteById(id);
    }

    @Override
    public void UpdateById(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        category.setUpdateUser(BaseContext.getCurrentId());
        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.UpdateById(category);
    }

    @Override
    public void StartOrStopCategory(Integer status, Long id) {
        Category category = new Category();
        category.setStatus(status);
        category.setId(id);
        categoryMapper.UpdateById(category);
    }
}
