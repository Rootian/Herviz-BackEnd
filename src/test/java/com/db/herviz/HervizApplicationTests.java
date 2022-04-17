package com.db.herviz;

import com.db.herviz.entity.Customer;
import com.db.herviz.service.CustomerService;
import com.db.herviz.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.sql.Wrapper;


@SpringBootTest(classes = HervizApplication.class)
@WebAppConfiguration
@Slf4j
class HervizApplicationTests {

    @Resource
    private CustomerServiceImpl customerService;

    @Test
    void contextLoads() {
        Customer byId = customerService.getById(1);
        log.info(byId.toString());
    }

}
