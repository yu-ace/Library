package com.yk.librarybook.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.yk.librarybook.model.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderDao {
    public static void save(List<Reader> readerList){
        String str = JSONUtil.toJsonStr(readerList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/lib/reader.dat");
        fileWriter.write(str);
    }

    public static List<Reader> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/lib/reader.dat");
            String str = fileReader.readString();
            List<Reader> readerList = JSONUtil.toList(str, Reader.class);
            return readerList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
