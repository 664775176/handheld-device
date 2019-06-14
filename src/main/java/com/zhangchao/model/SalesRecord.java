package com.zhangchao.model;

import com.zhangchao.util.Assert;
import com.zhangchao.util.StringUtils;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:PurchaseRecord
 * @description: SalesRecord Model
 * @date 2019/6/11 15:54
 */
public class SalesRecord {
    public String getMerchandiseCode() {
        return merchandiseCode;
    }

    public String getRecordDate() { return recordDate; }

    public int getSaleCount() {
        return saleCount;
    }

    public SalesRecord(String merchandiseCode, String recordDate, int saleCount){
        Assert.assertRuntimeExpression(StringUtils.notNull(merchandiseCode), "merchandiseCode cannot null!");
        Assert.assertRuntimeExpression(StringUtils.notNull(recordDate), "recordDate cannot null!");
        Assert.assertRuntimeExpression(saleCount > 0, "saleCount must > 0!");
        this.merchandiseCode = merchandiseCode;
        this.recordDate = recordDate;
        this.saleCount = saleCount;
    }

    private String merchandiseCode;
    private String recordDate;
    private int saleCount;
}
