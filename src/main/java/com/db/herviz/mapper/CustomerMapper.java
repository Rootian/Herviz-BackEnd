package com.db.herviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.herviz.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Chen Weiqi
 * @Date: 2022/4/17 16:57
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
