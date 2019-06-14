package com.zhangchao.enumeration;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:BusinessEventType
 * @description: BusinessEventType Enum
 * @date 2019/6/11 17:16
 */
public enum BusinessEventType {
    /**
     * 新进商品
     **/
    NEW("NEW", "新进"),

    /**
     * 进货
     **/
    PURCHASE("PURCHASE", "进货"),

    /**
     * 售货
     **/
    SALES("SALES", "售货"),

    /*
     * 未定义的
     */
    UNDEFINED("UNDEFINED", "未定义");

    private final String value;

    private final String name;

    BusinessEventType(String value, String name){
        this.value = value;
        this.name = name;
    }

    public static BusinessEventType findByValue(String value){
        switch(value){

            case "NEW":
                return NEW;
            case "PURCHASE":
                return PURCHASE;
            case "SALES":
                return SALES;
            default:
                return UNDEFINED;
        }
    }
}
