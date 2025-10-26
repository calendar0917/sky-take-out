package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {

    private final SetmealMapper setmealMapper;

    @Override
    public void addSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.addSetmeal(setmeal);
    }

    @Override
    public PageResult pageQuerySetmeal(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<Setmeal> records = setmealMapper.pageQuerySetmeal(setmealPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(records.getTotal());
        pageResult.setRecords(records.getResult());
        return pageResult;
    }
}
