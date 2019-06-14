package com.zhangchao.util;

import com.zhangchao.exception.BusinessException;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:Assert
 * @description: Assert
 * @date 2019/6/11 16:53
 */
public class Assert {
    public static void assertRuntimeExpression(String message, Throwable cause){
        throw new RuntimeException(message, cause);
    }

    public static void assertRuntimeExpression(boolean expression, String message){
        if(!expression)
            throw new RuntimeException(message);
    }

    public static void assertBusinessExpression(boolean expression, String message){
        if(!expression)
            throw new BusinessException(message);
    }

    public static void assertBusinessExpression( String message){
        assertBusinessExpression(false, message);
    }
    private Assert(){

    }
}
