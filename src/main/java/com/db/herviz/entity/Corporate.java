package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-05-07
 */
@Data
@TableName("zcq_corporate")
public class Corporate {

    @TableId(value = "corp_id", type = IdType.AUTO)
    private Long id;

    private String corpName;

    private Long regisNo;
}
