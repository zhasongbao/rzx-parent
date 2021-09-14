package com.rzx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 *
 * @author zy
 * @date 2021/06/03
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.rzx"})
public class RzxAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RzxAppApplication.class, args);
        System.out.println("===================================" +
                "========任智行App启动成功=========" +
                "===================================");
    }
}
