package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/18 19:17
 */
@Data
@TableName("zcq_office")
public class Office {

    @TableId(value = "ofc_id", type = IdType.AUTO)
    private Long id;

    private String streetAddr;

    private Long phoneNo;

    private String state;

    private String city;

    private Long zipCode;

}
