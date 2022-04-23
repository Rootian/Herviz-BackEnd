package com.db.herviz.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.db.herviz.entity.Invoice;
import com.db.herviz.entity.Payment;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:43
 */
public interface PaymentService extends IService<Payment> {

    void payInvoice(Invoice invoice, JSONArray payList);
}
