package com.sky.controller.admin;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sky.dto.CategoryDTO;

@RestController
@Slf4j
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /*
    * 新增菜品分类
    * @param categotyDTO
     */
    @PostMapping
    @ApiOperation(value = "新增分类")
    public Result addCatagory(@RequestBody CategoryDTO categoryDTO){
        log.info("新增菜品分类");
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /*
     * 分类分页查询
     * @param categoryPageQueryDTO
     */
    @GetMapping("/page")
    @ApiOperation(value = "分类分页查询")
    public Result<PageResult> pageQureyCategory(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询菜品{}",categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQueryCategory(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
     * 根据id删除分类
     * @param id
     */
    @DeleteMapping
    @ApiOperation(value = "根据id删除分类")
    public Result DeleteById(Long id){
        log.info("根据id删除{}",id);
        categoryService.DeleteById(id);
        return Result.success();
    }

    /*
     * 修改分类
     * @param categoryDTO
     */
    @PutMapping
    @ApiOperation(value = "修改菜品")
    public Result UpdateById(@RequestBody CategoryDTO categoryDTO){
        categoryService.UpdateById(categoryDTO);
        return Result.success();
    }

    /*
     * 启用、禁用分类
     * @param status
     * @param id
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用、禁用菜单")
    public Result StartOrStopCategory(@PathVariable Integer status,Long id){
         categoryService.StartOrStopCategory(status,id);
         return Result.success();
    }

    /*
     * 根据类型查询分类
     * @param type
     */

}
