package com.rzx.admin;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
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
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@ComponentScan({"com.rzx"})
public class RzxAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(RzxAdminApplication.class, args);
        System.out.println("===================================" +
                "========任智行管理系统启动成功=========" +
                "===================================");
    }
}
