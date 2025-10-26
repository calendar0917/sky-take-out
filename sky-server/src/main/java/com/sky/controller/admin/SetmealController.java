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
@Api(tags = "套餐相关接口")
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

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除套餐")
    public Result deleteSetmeals(@RequestParam List<Long> ids){
        setmealService.deleteSetmeals(ids);
        return Result.success();
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询套餐")
    public Result getById(@PathVariable Long id){
        Setmeal setmeal = setmealService.getById(id);
        return Result.success(setmeal);
    }

    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改套餐")
    public Result changeSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用、禁用套餐")
    public Result startOrStop(@PathVariable Integer status,@RequestParam Long id){
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setStatus(status);
        setmealDTO.setId(id);
        setmealService.update(setmealDTO);
        return Result.success();
    }
}
