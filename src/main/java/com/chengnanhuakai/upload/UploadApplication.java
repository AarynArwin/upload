package com.chengnanhuakai.upload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chengnanhuakai.upload.mapper")
//@ServletComponentScan(basePackages = {"com.chengnanhuakai.upload.filter"})
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}
