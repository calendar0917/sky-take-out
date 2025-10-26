package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    private final SetmealService setmealService;

    /**
     * 新增菜品
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增菜品")
    public Result addSetmeal(@RequestBody SetmealDTO setmealDTO) {
        setmealService.addSetmeal(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    public Result<PageResult> pageQuerySetmeal(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult = setmealService.pageQuerySetmeal(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation(value = "删除套餐")
    public Result deleteSetmeal(List<Long> ids)
}
