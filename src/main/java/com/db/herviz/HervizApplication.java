package com.db.herviz;

import com.db.herviz.service.CustomerService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
public class HervizApplication {



    public static void main(String[] args) {
        SpringApplication.run(HervizApplication.class, args);

    }

}
