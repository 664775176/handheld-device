package com.zhangchao.util;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:PrintUtil
 * @description: PrintUtil
 * @date 2019/6/12 18:49
 */
public class PrintUtil {
    private static String format = "Merchandise: %s, Inventory Quantity: %d, Inventory Amount: %.1f, Sales Quantity: %d, Sales Amount: %.1f, Profit: %.1f";

    private PrintUtil(){

    }
    public static void print(Object... objects){
        System.out.println(String.format(format, objects));
    }
}
