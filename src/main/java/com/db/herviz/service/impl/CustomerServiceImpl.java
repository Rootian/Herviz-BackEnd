package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Customer;
import com.db.herviz.mapper.CustomerMapper;
import com.db.herviz.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/17 16:53
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Override
    public Customer getCustomerByUId(Long uId) {
        return getOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getUId, uId));
    }
}
