package com.db.herviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.herviz.entity.Invoice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 20:55
 */
@Mapper
public interface InvoiceMapper extends BaseMapper<Invoice> {
}
