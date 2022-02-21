package com.rzx.app;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动程序
 *
 * @author zy
 * @date 2021/06/03
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@ComponentScan({"com.rzx"})
@EnableSwagger2
public class RzxAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RzxAppApplication.class, args);
        System.out.println("===================================" +
                "========任智行App启动成功=========" +
                "===================================");
    }
}
