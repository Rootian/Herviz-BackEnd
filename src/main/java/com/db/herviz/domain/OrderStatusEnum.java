package com.db.herviz.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;

public enum OrderStatusEnum{
    CREATED(1),
    PAID(2),
    COMPLETED(3),
    CANCELED(4);


    OrderStatusEnum(int code) {
        this.code = code;
    }

    @EnumValue
    private Integer code;




}
