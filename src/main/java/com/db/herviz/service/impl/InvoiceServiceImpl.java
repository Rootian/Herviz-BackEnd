package com.db.herviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.entity.Invoice;
import com.db.herviz.mapper.InvoiceMapper;
import com.db.herviz.service.InvoiceService;
import org.springframework.stereotype.Service;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 20:56
 */
@Service
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements InvoiceService {
}
