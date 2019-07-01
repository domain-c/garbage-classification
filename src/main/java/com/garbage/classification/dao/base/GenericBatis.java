package com.garbage.classification.dao.base;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author: domain
 * @date: 2018/9/5
 * 标识MyBatis的DAO,方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface GenericBatis {
    String value() default "";
}
