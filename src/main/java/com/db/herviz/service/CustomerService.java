package com.db.herviz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.Customer;
import org.springframework.stereotype.Service;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/17 16:53
 */
public interface CustomerService extends IService<Customer> {

    public Customer getCustomerByUId (int uId);
}
