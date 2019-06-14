package com.zhangchao.model;

import com.zhangchao.util.Assert;
import com.zhangchao.util.StringUtils;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:Merchandise
 * @description: Merchandise Model
 * @date 2019/6/11 15:26
 */
public class Merchandise {
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public Merchandise(String code, String name, double salePrice){
        Assert.assertRuntimeExpression(StringUtils.notNull(code), "code cannot null!");
        Assert.assertRuntimeExpression(StringUtils.notNull(name), "recordDate cannot null!");
        this.code = code;
        this.name = name;
        this.salePrice = salePrice;
    }

    public int hashCode(){
        return code.hashCode() * 31;
    }

    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object instanceof Merchandise) {
            Merchandise anotherString = (Merchandise)object;
            if(this.code.equals(anotherString.getCode())) {
                return true;
            }
        }
        return false;
    }
    //商品编号
    private String code;
    //商品名称
    private String name;
    //商品售价
    private double salePrice;

}
