package com.zhangchao.exception;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:BusinessException
 * @description: BusinessException Exception
 * @date 2019/6/12 14:01
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }


    public BusinessException(String s) {
        super(s);
    }
}
