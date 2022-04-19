package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 20:16
 */
@Data
@TableName("zcq.invoice")
public class Invoice {

    @TableId("invo_id")
    private Long id;

    @TableField("invo_date")
    private Date date;

    private Double amount;
}
