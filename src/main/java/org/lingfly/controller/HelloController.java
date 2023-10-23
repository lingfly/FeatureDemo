package org.lingfly.controller;


import org.lingfly.entity.TestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {



    @RequestMapping(value = "/hello")
    public TestEntity hello(@RequestParam Integer cnt){
        System.out.println(Thread.currentThread().getName() + ": cnt="+cnt);
        StringBuilder sb = new StringBuilder();

        sb.append("1".repeat(Math.max(0, cnt)));
        TestEntity entity = new TestEntity();
        entity.setString(sb.toString());
        return entity;
    }
}
