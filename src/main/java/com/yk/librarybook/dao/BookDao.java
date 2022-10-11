package com.yk.librarybook.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.yk.librarybook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public static void save(List<Book> bookList){
        String str = JSONUtil.toJsonStr(bookList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/lib/book.dat");
        fileWriter.write(str);
    }

    public static List<Book> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/lib/book.dat");
            String str = fileReader.readString();
            List<Book> bookList = JSONUtil.toList(str, Book.class);
            return bookList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
