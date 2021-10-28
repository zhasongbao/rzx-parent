package com.rzx.app.test;

import com.alibaba.fastjson.JSONObject;
import com.rzx.app.BaseTest;
import com.rzx.common.core.mongodb.MongodbService;
import com.rzx.common.utils.spring.SpringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * @author zhasbao
 * @version 1.0
 * @description
 * @date 2021/10/28 17:35
 */
public class Test1 extends BaseTest {
    @Autowired
    private MongodbService mongodbService;

    @Test
    public void testMongo() throws Exception{
        Query query = new Query();
        query.addCriteria(new Criteria("phone").is("10086"));
        JSONObject jsonResp = mongodbService.findOne(query, "testTable", JSONObject.class);
        if(ObjectUtils.isEmpty(jsonResp)){
            JSONObject json = new JSONObject();
            json.put("phone","10086");
            json.put("name","张三");
            json.put("crateTime", LocalDateTime.now().toString());
            mongodbService.save(json,"testTable");
        }
        jsonResp = mongodbService.findOne(query, "testTable", JSONObject.class);
        Query query2 = new Query();
        query2.addCriteria(new Criteria("phone").is("10086"));
        Update update = new Update();
        update.set("name","李四");
        mongodbService.update(query2,update,"testTable",JSONObject.class);
        jsonResp = mongodbService.findOne(query, "testTable", JSONObject.class);
    }
}
