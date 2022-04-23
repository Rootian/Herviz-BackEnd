package com.db.herviz.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.db.herviz.domain.BusinessException;
import com.db.herviz.domain.OrderStatusEnum;
import com.db.herviz.entity.Invoice;
import com.db.herviz.entity.Payment;
import com.db.herviz.entity.RentalOrder;
import com.db.herviz.mapper.PaymentMapper;
import com.db.herviz.service.PaymentService;
import com.db.herviz.service.RentalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:43
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
        implements PaymentService {

    @Autowired
    private RentalOrderService orderService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void payInvoice(Invoice invoice, JSONArray payList) throws BusinessException {
        for (int i = 0; i < payList.size(); i++) {
            // pay each payment
            JSONObject pay = payList.getJSONObject(i);
            Double amount = pay.getDouble("amount");
            String method = pay.getString("method");
            Long cardNo = pay.getLong("cardNo");
            try {
                payAmount(invoice.getId(), amount, method, cardNo);
                savePayment(invoice.getId(), amount, method, cardNo);

            } catch (BusinessException e) {
                throw new BusinessException(e.getMessage());
            }
        }
        orderService.setOrderStatus(invoice.getOrderId(), OrderStatusEnum.PAID);


    }

    private void payAmount(Long invoiceId, Double amount, String method, Long cardNo) {
        // actual payment
    }

    private void savePayment(Long invoiceId, Double amount, String method, Long cardNo) {
        // save payment record
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setCardNo(cardNo);
        payment.setInvoId(invoiceId);
        payment.setPayDate(new Date());
        save(payment);
    }

}
