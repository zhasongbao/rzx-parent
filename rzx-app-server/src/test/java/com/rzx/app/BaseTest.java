package com.rzx.app;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 *
 * @author hk
 * @date: 2021-09-14
 */
@SpringBootTest
@MapperScan(basePackages = {"com.rzx.**.mapper"})
@ComponentScan({"com.rzx.**","com.rzx.project"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
}
