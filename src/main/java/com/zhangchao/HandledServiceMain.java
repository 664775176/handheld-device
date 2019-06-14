package com.zhangchao;

import com.zhangchao.model.Merchandise;
import com.zhangchao.model.PurchaseAndInventoryRecord;
import com.zhangchao.parse.Parse;
import com.zhangchao.util.PrintUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:Main
 * @description: HandledServiceMain Class
 * @date 2019/6/11 17:22
 */
public class HandledServiceMain {
    private static final String FILE_PATH =  "/demo.txt";


    public static void main(String []args){
        //如果有输出参数用第一个参数作为解析文件路径
        String path = HandledServiceMain.class.getResource(FILE_PATH).getFile();
        if(args != null && args.length != 0) {
            path = HandledServiceMain.class.getResource(args[0]).getFile();
        }
        Map<String, Merchandise> merchandiseMap = new HashMap<>();
        Map<String, List<PurchaseAndInventoryRecord>> purchaseRecordMap = new HashMap<>();
        //解析文件得到结果
        Parse.parseFile(path, merchandiseMap, purchaseRecordMap);
        //输出结果
        printResult(merchandiseMap, purchaseRecordMap);
    }

    /**
     * 输出结果
     * @param merchandiseMap
     * @param purchaseRecordMap
     */
    private static void printResult(Map<String, Merchandise> merchandiseMap, Map<String, List<PurchaseAndInventoryRecord>> purchaseRecordMap) {
        for(Map.Entry<String, List<PurchaseAndInventoryRecord>> entry: purchaseRecordMap.entrySet()) {
            int inventoryCount = 0;
            double inventoryAmount = 0;
            int saleCount = 0;
            double soldAmount = 0;
            for(PurchaseAndInventoryRecord record : entry.getValue()) {
                inventoryCount = inventoryCount + record.getInventoryCount();
                inventoryAmount = inventoryAmount + record.getInventoryCount() * record.getPurchasePrice();
                saleCount = saleCount + (record.getPurchaseCount() - record.getInventoryCount());
                soldAmount = soldAmount + (record.getPurchaseCount() - record.getInventoryCount()) * record.getPurchasePrice();
            }
            double salePrice = merchandiseMap.get(entry.getKey()).getSalePrice();
            double saleAmount = saleCount * salePrice;
            PrintUtil.print(merchandiseMap.get(entry.getKey()).getName(), inventoryCount, inventoryAmount, saleCount, saleAmount, (saleAmount - soldAmount));
        }
    }
}
