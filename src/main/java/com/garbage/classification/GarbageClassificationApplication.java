package com.garbage.classification;

import com.garbage.classification.dao.base.GenericBatis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenguotu
 */
@SpringBootApplication
@MapperScan(basePackages = "com.garbage.classification.dao", annotationClass = GenericBatis.class)
public class GarbageClassificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarbageClassificationApplication.class, args);
    }

}
