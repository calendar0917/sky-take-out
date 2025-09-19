package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    PageResult pageQueryCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    void addCategory(CategoryDTO categotyDTO);

    void DeleteById(Integer id);

    void UpdateById(CategoryDTO categoryDTO);

    void StartOrStopCategory(Integer status, Long id);
}
