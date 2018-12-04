package com.model.utills.log;

import java.lang.annotation.*;

/**
 * zxl
 * 2018-12-03
 * 自定义日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Inherited
public @interface SysLog {

    String moduleName() default "";   //自定义参数

}
