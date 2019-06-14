package com.zhangchao.model;

import com.zhangchao.util.Assert;
import com.zhangchao.util.StringUtils;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:PurchaseRecord
 * @description: PurchaseAndInventoryRecord Model
 * @date 2019/6/11 15:54
 */
public class PurchaseAndInventoryRecord {
    public String getMerchandiseCode() {
        return merchandiseCode;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getInventoryCount() { return inventoryCount; }

    public void setInventoryCount(int inventoryCount) { this.inventoryCount = inventoryCount; }

    public PurchaseAndInventoryRecord(String merchandiseCode, String recordDate, int purchaseCount, double purchasePrice){
        Assert.assertRuntimeExpression(StringUtils.notNull(merchandiseCode), "merchandiseCode cannot null!");
        Assert.assertRuntimeExpression(StringUtils.notNull(recordDate), "recordDate cannot null!");
        Assert.assertRuntimeExpression(purchaseCount > 0, "purchaseCount must > 0!");
        Assert.assertRuntimeExpression(purchasePrice > 0, "purchasePrice must > 0!");
        this.merchandiseCode = merchandiseCode;
        this.recordDate = recordDate;
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
        this.inventoryCount = purchaseCount;  //init purchaseCount
    }
    //商品编号
    private String merchandiseCode;
    //采购日期
    private String recordDate;
    //采购数量
    private int purchaseCount;
    //采购价格
    private double purchasePrice;
    //库存数量
    private int inventoryCount;
}
