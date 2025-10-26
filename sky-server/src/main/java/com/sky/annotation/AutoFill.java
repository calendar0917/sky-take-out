package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 */
@Target(ElementType.METHOD)  // 定义声明周期
@Retention(RetentionPolicy.RUNTIME) // 定义使用范围
public @interface AutoFill {
    //数据库操作类型：UPDATE INSERT
    OperationType value();
}
