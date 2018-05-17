import java.net.InetSocketAddress;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;  
  
import com.alibaba.otter.canal.client.CanalConnector;  
import com.alibaba.otter.canal.common.utils.AddressUtils;  
import com.alibaba.otter.canal.protocol.Message;
import com.sun.istack.internal.NotNull;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;  
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;  
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;  
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;  
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;  
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;  
import com.alibaba.otter.canal.client.*;  
  

public class ClientSample {  
  
    public static void main(String args[]) {  
        // 创建链接  
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),  
//                11111), "example", "", "");  
//        String hostIp = AddressUtils.getHostIp();
//        System.out.println(hostIp);
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("10.0.0.222", 11111), "example", "", "");
        int batchSize = 1000;  
        int emptyCount = 0;  
        try {  
            connector.connect();  
            connector.subscribe(".*\\..*");  
            connector.rollback();  
            int totalEmtryCount = 1200;  
            while (emptyCount < totalEmtryCount) {  
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据  
                long batchId = message.getId();  
                int size = message.getEntries().size();  
                if (batchId == -1 || size == 0) {  
                    emptyCount++;  
                    System.out.println("empty count : " + emptyCount);  
                    try {  
                        Thread.sleep(1000);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                } else {  
                    emptyCount = 0;  
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);  
                    printEntry(message.getEntries());  
                }  
  
                connector.ack(batchId); // 提交确认  
                // connector.rollback(batchId); // 处理失败, 回滚数据  
            }  
  
            System.out.println("empty too many times, exit");  
        } finally {  
            connector.disconnect();  
        }  
    }  
  
    private static void printEntry(@NotNull List<Entry> entrys) {  
        for (Entry entry : entrys) {  
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {  
                continue;  
            }  
  
            RowChange rowChage = null;  
            try {  
            	// 数据反序列化
                rowChage = RowChange.parseFrom(entry.getStoreValue());  
            } catch (Exception e) {  
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),  
                        e);  
            }  
            
            // 获取操作类型   新增 UPDATE 删除DELETE
            EventType eventType = rowChage.getEventType();
            // 获取数据库信息
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",  
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),  
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),  
                    eventType));  
  
            for (RowData rowData : rowChage.getRowDatasList()) { 
            	// 删除操作
                if (eventType == EventType.DELETE) {  
                    printColumn(rowData.getBeforeColumnsList());  
                } else if (eventType == EventType.INSERT) {  
                //  新增操作
                    printColumn(rowData.getAfterColumnsList());  
                } else {  
                    System.out.println("-------> before");  
                    printColumn(rowData.getBeforeColumnsList());  
                    System.out.println("-------> after");  
                    printColumn(rowData.getAfterColumnsList());  
                }  
            }  
        }  
    }  
  
    private static void printColumn(@NotNull List<Column> columns) {  
        for (Column column : columns) {  
        	// uid : 3    update=true
            System.err.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());  
        }  
    }  
}  