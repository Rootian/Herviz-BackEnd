package com.db.herviz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-20
 */
@Data
@TableName("zcq_user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
}
