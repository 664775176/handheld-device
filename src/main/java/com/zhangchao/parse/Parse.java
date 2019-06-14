package com.zhangchao.parse;

import com.zhangchao.enumeration.BusinessEventType;
import com.zhangchao.exception.BusinessException;
import com.zhangchao.model.Merchandise;
import com.zhangchao.model.PurchaseAndInventoryRecord;
import com.zhangchao.model.SalesRecord;
import com.zhangchao.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:Parse
 * @description: Parse
 * @date 2019/6/11 17:25
 */
public class Parse {

    private static Logger logger = LoggerFactory.getLogger(Parse.class);
    private static final String SEPARATOR  = " ";

    /**
     * 解析文件
     * @param path
     * @param merchandiseMap
     * @param purchaseRecordMap
     */
    public static void parseFile(String path, Map<String, Merchandise> merchandiseMap, Map<String, List<PurchaseAndInventoryRecord>> purchaseRecordMap) {
        String line = null;
        try (LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(new FileInputStream(path)))) {
            //构造LineNumberReader实例
            while ((line = lineNumberReader.readLine()) != null) {
                logger.debug("Line number:{}, line: {}", lineNumberReader.getLineNumber(), line);
                parseLine(line, merchandiseMap, purchaseRecordMap);
            }
        } catch (IOException e) {
            logger.error("error reading file");
            Assert.assertRuntimeExpression("fail to read file:", e);
        } catch (BusinessException e) {
            logger.error("business error：" + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("error parsing file, error line: {}", line);
            Assert.assertRuntimeExpression("error parsing file", e);
        }
    }

    /**
     * 逐行解析
     * @param line
     * @param merchandiseMap
     * @param purchaseRecordMap
     * @throws RuntimeException
     */
    private static void parseLine(String line, Map<String, Merchandise> merchandiseMap, Map<String, List<PurchaseAndInventoryRecord>> purchaseRecordMap) throws RuntimeException{
        //这里假设以空格作为分隔符
        String[] lineList = line.split(SEPARATOR);
        //这里假设以第二个字段作为类型判断
        switch (BusinessEventType.findByValue(lineList[1])) {
            case NEW:
                Merchandise merchandise = new Merchandise(lineList[2], lineList[3], Double.parseDouble(lineList[4]));
                Assert.assertBusinessExpression(!merchandiseMap.containsKey(merchandise.getCode()),"Barcode "+merchandise.getCode() + " already exist.");
                merchandiseMap.put(merchandise.getCode(), merchandise);
                break;
            case PURCHASE:
                PurchaseAndInventoryRecord purchaseAndInventoryRecord = new PurchaseAndInventoryRecord(lineList[2], lineList[0], Integer.parseInt(lineList[3]), Double.parseDouble(lineList[4]));
                Assert.assertBusinessExpression(merchandiseMap.containsKey(purchaseAndInventoryRecord.getMerchandiseCode()),
                        "Did not found merchandise with barcode equals " + purchaseAndInventoryRecord.getMerchandiseCode());
                List<PurchaseAndInventoryRecord> purchaseAndInventoryRecordList = purchaseRecordMap.get(purchaseAndInventoryRecord.getMerchandiseCode());
                if(purchaseAndInventoryRecordList == null){
                    purchaseAndInventoryRecordList = new ArrayList<>();
                }
                purchaseAndInventoryRecordList.add(purchaseAndInventoryRecord);
                purchaseRecordMap.put(purchaseAndInventoryRecord.getMerchandiseCode(), purchaseAndInventoryRecordList);
                break;
            case SALES:
                SalesRecord salesRecord = new SalesRecord(lineList[2], lineList[0], Integer.parseInt(lineList[3]));
                Assert.assertBusinessExpression(merchandiseMap.containsKey(salesRecord.getMerchandiseCode()),
                        "Did not found merchandise with barcode equals " + salesRecord.getMerchandiseCode());
                List<PurchaseAndInventoryRecord> purchaseAndInventoryRecords = purchaseRecordMap.get(salesRecord.getMerchandiseCode());
                Assert.assertBusinessExpression( purchaseAndInventoryRecords != null,"Do not have enough merchandise " + salesRecord.getMerchandiseCode());
                Iterator<PurchaseAndInventoryRecord> iterator = purchaseAndInventoryRecords.iterator();
                int lastSaleCount = salesRecord.getSaleCount();
                while(iterator.hasNext()) {
                    PurchaseAndInventoryRecord record = iterator.next();
                    if(lastSaleCount >= record.getInventoryCount()){
                        lastSaleCount = lastSaleCount - record.getInventoryCount();
                        record.setInventoryCount(0);
                    } else {
                        record.setInventoryCount(record.getInventoryCount() - lastSaleCount);
                        lastSaleCount = 0;
                        break;
                    }
                }
                //到这一步lastSaleCount还大于0说明已经是库存数量不够了
                Assert.assertBusinessExpression( lastSaleCount <= 0, "Do not have enough merchandise " + salesRecord.getMerchandiseCode());
                break;
            default:
                Assert.assertBusinessExpression("Failed to parse: " + line);
        }
    }
    private Parse(){

    }
}
