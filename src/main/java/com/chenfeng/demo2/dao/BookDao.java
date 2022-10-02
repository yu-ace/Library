package com.chenfeng.demo2.dao;

import com.alibaba.fastjson2.JSON;
import com.chenfeng.demo2.model.Book;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookDao {

    public static List<Book> load() throws IOException {
        String s = FileUtils.readFileToString(new File("data"), "utf8");
        List<Book> bookList = JSON.parseArray(s, Book.class);
        return bookList;
    }



    public static void save(Book[] books){
        String s = JSON.toJSONString(books);
        try {
            FileUtils.write(new File("data"),s,"utf8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
