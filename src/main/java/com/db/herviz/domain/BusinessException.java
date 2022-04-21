package com.db.herviz.domain;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-21
 */
public class BusinessException extends RuntimeException{
    private int code;

    private String errMsg;

    public BusinessException(String message) {
        super(message);
    }

}
