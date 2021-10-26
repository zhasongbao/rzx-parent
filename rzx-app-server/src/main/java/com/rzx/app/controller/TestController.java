package com.rzx.app.controller;

import com.rzx.common.core.controller.BaseController;
import com.rzx.common.core.page.TableDataInfo;
import com.rzx.project.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 测试Controller
 *
 * @author zhasbao
 * @date 2021-10-13
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @PostMapping("/pool")
    public TableDataInfo list(@RequestBody @Validated OrderInfo dto){
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPoolTaskExecutor.execute(()->logger.error(String.valueOf(finalI)));
        }
        return getDataTable(new ArrayList<>());
    }

}
