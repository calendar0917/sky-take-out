package com.sky.controller.admin;


import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("管理端接口")
@Slf4j
@Api(tags = "商店相关接口")
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation(value = "更改商店状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态为{}",status == 1 ? "营业中":"休息中");
        redisTemplate.opsForValue().set("SHOP_STATUS", status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation(value = "查询商店状态")
    public Result getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get("SHOP_STATUS");
        return Result.success(status);
    }
}


