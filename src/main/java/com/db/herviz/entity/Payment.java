package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 18:39
 */
@Data
@TableName("zcq_payment")
public class Payment {

    @TableId(value = "pay_id", type = IdType.AUTO)
    private Long id;

    private Date payDate;

    private String method;

    private Double amount;

    private Long cardNo;

    private Long invoId;
}
