package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {

    private final SetmealDishMapper setmealDishMapper;
    private DishMapper dishMapper;
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishflavor -> {
                dishflavor.setDishId(dishId);
            });
            // 向口味表中插入 n 条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> records = dishMapper.pageQuery(dishPageQueryDTO);
        Long total = records.getTotal();
        List<DishVO> dishVOS = records.getResult();
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(dishVOS);
        return pageResult;
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        // 判断当前商品是否能删除 --> 是否起售
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() != StatusConstant.DISABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 判断当前菜品是否被菜单关联
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByIds(ids);
        if (setmealIds != null && setmealIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        // 删除菜单中数据
        for(Long id : ids) {
            dishMapper.deleteById(id);
            //删除菜单关联的口味数据
            dishFlavorMapper.deleteByDishId(id);
        }
    }

    @Override
    public DishVO getByIdwithFlavor(Long id) {
        // 查菜品
        Dish dish = dishMapper.getById(id);
        // 查口味
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);
        // 将数据封装到 VO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;

    }

    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        // 修改菜品表基本信息
        dishMapper.update(dish);
        // 重新插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishflavor -> {
                dishflavor.setDishId(dish.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }

    }
}
