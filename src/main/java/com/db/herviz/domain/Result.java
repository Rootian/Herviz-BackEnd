package com.db.herviz.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-17
 */
@Data
public class Result implements Serializable {

    private Integer code;

    private Object data;

    private String message;

    public Result(Integer _code, Object _data, String _message) {
        code = _code;
        data = _data;
        message = _message;
    }
    public Result(Integer _code, Object _data) {
        code = _code;
        data = _data;
    }
}
