package com.chenfeng.demo2;

import com.alibaba.fastjson2.JSON;
import com.chenfeng.demo2.model.HistoryItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HistoryManager {

    private static HistoryItem[] historyItems = new HistoryItem[100];
    private static int size;

    public static void save(HistoryItem item){
        historyItems[size] = item;
        size++;
    }

    public static HistoryItem[] getHistoryItems() {
        return historyItems;
    }

    public static int getSize() {
        return size;
    }


    public static void load() throws IOException {
        String s = FileUtils.readFileToString(new File("historyData"), "utf8");
        List<HistoryItem> historyItemList = JSON.parseArray(s, HistoryItem.class);
        for (HistoryItem historyItem :historyItemList) {
            if(historyItem == null){
                break;
            }
            historyItems[size] = historyItem;
            size++;
        }
    }

    public static void save(){
        String s = JSON.toJSONString(historyItems);
        try {
            FileUtils.write(new File("historyData"),s,"utf8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
