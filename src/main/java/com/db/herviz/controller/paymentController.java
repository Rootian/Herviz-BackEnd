package com.db.herviz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.ResponseX;
import com.db.herviz.entity.Invoice;
import com.db.herviz.service.InvoiceService;
import com.db.herviz.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-22
 */
@RestController
@RequestMapping("/api/payment")
@Api
public class paymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String payInvoice(@RequestBody String body) {
        JSONObject obj = JSONObject.parseObject(body);
        Long invoiceId = obj.getLong("invoiceId");
        Invoice invoice = invoiceService.getById(invoiceId);
        JSONArray payList = obj.getJSONArray("payList");
        try {
            paymentService.payInvoice(invoice, payList);
        } catch (BusinessException e) {
            return ResponseX.fail(e.getMessage());
        }

        return ResponseX.success(null);
    }
}
