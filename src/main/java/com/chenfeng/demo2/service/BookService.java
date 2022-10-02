package com.chenfeng.demo2.service;


import com.chenfeng.demo2.HistoryManager;
import com.chenfeng.demo2.dao.BookDao;
import com.chenfeng.demo2.model.Book;
import com.chenfeng.demo2.model.HistoryItem;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {


    private static Book[] books = new Book[50];

    private static Map<Integer,Integer> map = new HashMap<>();
    private static int size;

    public static void newBook(int bookId,String name,String author,int category,double price,int count){
        Book book = new Book();
        book.setId(bookId);
        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        book.setPrice(price);
        books[size] = book;
        size++;

        HistoryItem item = new HistoryItem();
        item.setBookId(bookId);
        item.setType(0);
        item.setCount(count);
        item.setUserId(-1);
        item.setTime(new Date());
        HistoryManager.save(item);

    }


    public static void returnBook(int bookId,int userid,int count){
        HistoryItem item = new HistoryItem();
        item.setBookId(bookId);
        item.setType(2);
        item.setCount(count);
        item.setUserId(userid);
        item.setTime(new Date());
        HistoryManager.save(item);
    }

    public static void borrowBook(int bookId,int userid,int count){
        if(remains(bookId) > 0){
            HistoryItem item = new HistoryItem();
            item.setBookId(bookId);
            item.setType(1);
            item.setCount(count);
            item.setUserId(userid);
            item.setTime(new Date());
            HistoryManager.save(item);
        }
    }

    public static Book getBookById(int id){
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static int remains(int bookId){
        int sum =  0;
        HistoryItem[] historyItems = HistoryManager.getHistoryItems();
        for (int i = 0; i < HistoryManager.getSize(); i++) {
            if(historyItems[i].getBookId()==bookId){
                if(historyItems[i].getType()==1){
                    sum = sum - historyItems[i].getCount();
                }else {
                    sum = sum + (historyItems[i].getCount());
                }
            }
        }
        return sum;
    }

    public static Book[] getBooks() {
        return books;
    }

    public static void load() {
        try {
            List<Book> bookList = BookDao.load();
            for (Book book : bookList) {
                if(book==null){
                    break;
                }
                books[size] = book;
                size++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save() {
        BookDao.save(books);
    }

    public static void save(Book book){
        books[size] = book;
        size++;
    }
}
