package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;

import java.util.List;

public interface SetmealService {
    void addSetmeal(SetmealDTO setmealDTO);

    PageResult pageQuerySetmeal(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteSetmeals(List<Long> ids);

    Setmeal getById(Long id);

    void update(SetmealDTO setmealDTO);
}
