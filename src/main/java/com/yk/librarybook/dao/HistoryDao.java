package com.yk.librarybook.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.yk.librarybook.model.HistoryItem;
import com.yk.librarybook.model.Reader;

import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    public static void save(List<HistoryItem> historyItemList){
        String str = JSONUtil.toJsonStr(historyItemList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/lib/historyItem.dat");
        fileWriter.write(str);
    }

    public static List<HistoryItem> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/lib/historyItem.dat");
            String str = fileReader.readString();
            List<HistoryItem> historyItemList = JSONUtil.toList(str, HistoryItem.class);
            return historyItemList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
