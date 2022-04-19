package com.db.herviz;

import com.db.herviz.service.CustomerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class HervizApplication {

    @Resource
    private CustomerService customerService;


    public static void main(String[] args) {
        SpringApplication.run(HervizApplication.class, args);

    }

}
